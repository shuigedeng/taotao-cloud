package com.taotao.cloud.demo.sharding.jdbc.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.taotao.cloud.data.mybatis.plus.mapper.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user")
public class User extends SuperEntity {
	private static final long serialVersionUID = 8898492657846787286L;
	private String companyId;
	private String name;
}
