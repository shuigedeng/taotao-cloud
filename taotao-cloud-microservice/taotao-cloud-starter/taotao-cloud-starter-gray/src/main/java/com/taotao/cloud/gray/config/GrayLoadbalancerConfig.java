package com.taotao.cloud.gray.config;

import com.taotao.cloud.gray.rule.GrayRoundRobinLoadBalancer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

/**
 * 灰度负载配置类
 *
 * @author madi
 */
public class GrayLoadbalancerConfig {

	@Bean
	public ReactorLoadBalancer<ServiceInstance> reactorServiceInstanceLoadBalancer(
		Environment environment,
		LoadBalancerClientFactory loadBalancerClientFactory) {
		String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
		return new GrayRoundRobinLoadBalancer(
			loadBalancerClientFactory.getLazyProvider(name, ServiceInstanceListSupplier.class),
			name);
	}


}
