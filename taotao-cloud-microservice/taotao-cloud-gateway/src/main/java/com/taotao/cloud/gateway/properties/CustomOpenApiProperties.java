package com.taotao.cloud.gateway.properties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * 验证权限配置
 *
 * @date 2020-10-28
 */
@Data
@RefreshScope
@ConfigurationProperties(prefix = CustomOpenApiProperties.BASE_AUTHENTICATION_PREFIX)
public class CustomOpenApiProperties {

	public static final String BASE_AUTHENTICATION_PREFIX = "taotao.cloud.authentication";

	/**
	 * 监控中心和swagger需要访问的url
	 */
	private static final String[] ENDPOINTS = {
		"/oauth/**",
		"/actuator/**",
		"/v2/api-docs/**",
		"/swagger/api-docs",
		"/swagger-ui.html",
		"/doc.html",
		"/swagger-resources/**",
		"/webjars/**",
		"/druid/**",
		"/error/**",
		"/assets/**",
		"/auth/logout",
		"/auth/code",
		"/auth/sms-code"
	};

	/**
	 * 忽略URL，List列表形式
	 */
	private List<String> ignoreUrl = new ArrayList<>();

	/**
	 * 是否启用网关鉴权模式
	 */
	private Boolean enable = true;

	/**
	 * 首次加载合并ENDPOINTS
	 */
	@PostConstruct
	public void initIgnoreUrl() {
		Collections.addAll(ignoreUrl, ENDPOINTS);
	}
}
