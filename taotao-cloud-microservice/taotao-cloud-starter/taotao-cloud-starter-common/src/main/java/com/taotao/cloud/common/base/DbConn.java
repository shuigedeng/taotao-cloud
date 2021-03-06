/*
 * Copyright 2002-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.taotao.cloud.common.base;

import com.taotao.cloud.common.utils.ContextUtil;
import com.taotao.cloud.common.utils.LogUtil;
import com.taotao.cloud.common.utils.PropertyUtil;
import com.taotao.cloud.common.utils.StringUtil;
import com.taotao.cloud.common.utils.TimeWatchUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import lombok.Data;

/**
 * DbConn
 *
 * @author dengtao
 * @version 1.0.0
 * @since 2021/6/22 17:08
 **/
@Data
public final class DbConn implements AutoCloseable {

	Connection conn;

	public DbConn() {
		try {
			conn = ContextUtil.getBean(DataSource.class, true).getConnection();
		} catch (Exception e) {
			throw new DbException("获取数据库连接异常", "", e);
		}
	}

	public DbConn(DataSource dataSource) {
		try {
			conn = dataSource.getConnection();
		} catch (Exception e) {
			throw new DbException("获取数据库连接异常", "", e);
		}
	}

	public DbConn(String url, String user, String password, String driver) {
		try {
			// 加载数据库驱动
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			throw new DbException("获取数据库连接异常", "", e);
		}
	}

	private boolean getPrintSql() {
		return PropertyUtil.getPropertyCache(CoreProperties.TaoTaoCloudIsPrintSqlTimeWatch, true);
	}

	/**
	 * 关闭数据库连接
	 */
	@Override
	public void close() {
		TimeWatchUtil.print(getPrintSql(), "[db]close", () -> {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {
				throw new DbException("close", "", e);
			}
		});
	}

	public void beginTransaction(int level) {
		TimeWatchUtil.print(getPrintSql(), "[db]beginTransaction", () -> {
			try {
				if (conn != null) {
					conn.setAutoCommit(false);
					if (level > 0) {
						// Connection.
						conn.setTransactionIsolation(level);
					}
				}
			} catch (Exception e) {
				throw new DbException("beginTransaction", "", e);
			}
		});
	}

	public void commit() {
		TimeWatchUtil.print(getPrintSql(), "[db]commit", () -> {
			try {
				if (conn != null) {
					conn.commit();
					conn.setAutoCommit(true);
				}
			} catch (Exception e) {
				throw new DbException("commit", "", e);
			}
		});
	}

	public void rollback() {
		TimeWatchUtil.print(getPrintSql(), "[db]rollback", () -> {
			try {
				if (conn != null) {
					conn.rollback();
					conn.setAutoCommit(true);
				}
			} catch (Exception e) {
				// 此处不抛异常
				throw new DbException("rollback", "", e);
			}
		});
	}

	public int executeSql(final String sql, final Object[] parameterValues) {
		return (int) TimeWatchUtil.print(getPrintSql(), "[db]" + sql, () -> {
			try {
				PreparedStatement statement = conn.prepareStatement(sql);
				attachParameterObjects(statement, parameterValues);
				int result = statement.executeUpdate();
				return result;
			} catch (Exception e) {
				throw new DbException("executeSql", sql, e);
			}
		});
	}

	public Object executeScalar(final String sql, final Object[] parameterValues) {
		try {
			Object value = null;
			try (ResultSet rs = executeResultSet(sql, parameterValues)) {
				if (rs != null && rs.next()) {
					value = rs.getObject(1);
				}
				return value;
			}
		} catch (Exception e) {
			throw new DbException("executeScalar", sql, e);
		}
	}

	public ResultSet executeResultSet(final String sql, final Object[] parameterValues) {
		return (ResultSet) TimeWatchUtil.print(getPrintSql(), "[db]" + sql, () -> {
			try {
				PreparedStatement statement = conn.prepareStatement(sql);
				attachParameterObjects(statement, parameterValues);
				ResultSet rs = statement.executeQuery();
				// statement.clearParameters();
				return rs;
			} catch (Exception e) {
				throw new DbException("executeResultSet", sql, e);
			}
		});
	}

	public List<Map<String, Object>> executeList(final String sql, final Object[] parameterValues) {
		return TimeWatchUtil.print(getPrintSql(), "[db]" + sql, () -> {
			try {
				PreparedStatement statement = conn.prepareStatement(sql);
				attachParameterObjects(statement, parameterValues);
				List<Map<String, Object>> map = null;
				try (ResultSet rs = statement.executeQuery()) {
					map = toMapList(rs);
				}
				return map;

			} catch (Exception e) {
				throw new DbException("executeResultSet", sql, e);
			}
		});

	}

	public List<Map<String, Object>> toMapList(ResultSet rs) {
		try {
			List<Map<String, Object>> list = new ArrayList<>();
			if (rs != null && !rs.isClosed()) {
				ResultSetMetaData meta = rs.getMetaData();
				int colCount = meta.getColumnCount();
				int rowsCount = -1;
				while (rs.next()) {
					Map<String, Object> map = (rowsCount > 0 ? new HashMap(rowsCount)
						: new HashMap());
					for (int i = 1; i <= colCount; i++) {
						String key = meta.getColumnName(i);
						Object value = rs.getObject(i);
						map.put(key, value);
					}
					rowsCount = map.size();
					list.add(map);
				}
			}
			return list;
		} catch (Exception exp) {
			throw new DbException("toMapList", "", exp);
		}
	}

	protected void attachParameterObjects(PreparedStatement statement, Object[] values)
		throws Exception {
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				if (values[i] instanceof java.util.Date) {
					statement
						.setObject(i + 1, new Timestamp(((java.util.Date) values[i]).getTime()));
				} else {
					statement.setObject(i + 1, values[i]);
				}
			}
		}
	}

	public boolean tableIsExist(String tablename) {
		List<Map<String, Object>> ds = executeList("Select name from sysobjects where Name=?",
			new Object[]{tablename});
		if (ds == null || ds.size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	public class DbException extends RuntimeException {

		public DbException(String message, String sql, Exception e) {
			super(message, e);
			if (PropertyUtil.getPropertyCache("taotao.cloud.db.printSqlError.enabled", true)
				&& !StringUtil
				.isEmpty(sql)) {
				LogUtil.error("错误sql: {0}", e, sql);
			}
		}
	}
}
