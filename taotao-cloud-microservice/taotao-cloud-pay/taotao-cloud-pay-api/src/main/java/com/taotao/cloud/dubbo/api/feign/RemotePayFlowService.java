package com.taotao.cloud.dubbo.api.feign;

import com.taotao.cloud.common.constant.ServiceNameConstant;
import com.taotao.cloud.core.model.Result;
import com.taotao.cloud.dubbo.api.feign.fallback.RemotePayFlowFallbackImpl;
import com.taotao.cloud.dubbo.api.vo.PayFlowVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 远程调用快递公司模块
 *
 * @author dengtao
 * @since 2020/5/2 16:42
 */
@FeignClient(contextId = "RemotePayFlowService", value = ServiceNameConstant.TAOTAO_CLOUD_LOGISTICS_CENTER, fallbackFactory = RemotePayFlowFallbackImpl.class)
public interface RemotePayFlowService {

	/**
	 * 根据id查询支付信息
	 *
	 * @param id id
	 * @return com.taotao.cloud.core.model.Result<ChatbotVO>
	 * @author dengtao
	 * @since 2020/11/20 上午10:45
	 * @version 1.0.0
	 */
	@GetMapping("/pay/flow/info/id/{id:[0-9]*}")
	Result<PayFlowVO> findPayFlowById(@PathVariable(value = "id") Long id);
}

