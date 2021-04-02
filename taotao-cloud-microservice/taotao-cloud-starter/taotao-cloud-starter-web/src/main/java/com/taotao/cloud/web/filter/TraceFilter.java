package com.taotao.cloud.web.filter;

import com.taotao.cloud.common.utils.TraceUtil;
import com.taotao.cloud.web.properties.FilterProperties;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 日志链路追踪过滤器
 *
 * @date 2020-9-8
 */
public class TraceFilter extends OncePerRequestFilter {

	@Autowired
	private FilterProperties filterProperties;

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return !filterProperties.getTrace();
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {
		try {
			String traceId = TraceUtil.getTraceId(request);
			TraceUtil.mdcTraceId(traceId);
			filterChain.doFilter(request, response);
		} finally {
			MDC.clear();
		}

	}
}
