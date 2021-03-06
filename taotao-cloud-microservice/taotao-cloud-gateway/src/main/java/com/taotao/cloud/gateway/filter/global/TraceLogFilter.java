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
package com.taotao.cloud.gateway.filter.global;

import com.taotao.cloud.common.constant.CommonConstant;
import com.taotao.cloud.common.utils.IdGeneratorUtil;
import com.taotao.cloud.common.utils.TraceUtil;
import com.taotao.cloud.gateway.properties.CustomRequestTraceProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 生成日志链路追踪id
 *
 * @author dengtao
 * @version 1.0.0
 * @since 2020/4/29 22:13
 */
@Component
public class TraceLogFilter implements GlobalFilter, Ordered {

	private final CustomRequestTraceProperties customRequestTraceProperties;

	public TraceLogFilter(CustomRequestTraceProperties customRequestTraceProperties) {
		this.customRequestTraceProperties = customRequestTraceProperties;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		if (customRequestTraceProperties.getEnabled()) {
			String traceId = IdGeneratorUtil.getIdStr();
			TraceUtil.mdcTraceId(traceId);
			ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate()
				.headers(h -> h.add(CommonConstant.TAOTAO_CLOUD_TRACE_HEADER, traceId))
				.build();

			ServerWebExchange build = exchange.mutate().request(serverHttpRequest).build();
			return chain.filter(build);
		}
		return chain.filter(exchange);
	}

	@Override
	public int getOrder() {
		return 1;
	}
}

