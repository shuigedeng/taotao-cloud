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
package com.taotao.cloud.seata.configuration;

import com.zaxxer.hikari.HikariDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * DataSourceConfiguration
 *
 * @author dengtao
 * @version 1.0.0
 * @since 2020/10/22 14:48
 */
public class DataSourceConfiguration {

	@Primary
	@Bean(name = "seataDataSource")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource(DataSourceProperties properties) {
		HikariDataSource hikariDataSource =
			properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
		return new DataSourceProxy(hikariDataSource);
	}
}
