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
package com.taotao.cloud.portal.controller;

import cn.hutool.core.util.StrUtil;
import com.taotao.cloud.portal.util.Constants;
import com.taotao.cloud.portal.util.CookieUtil;
import com.taotao.cloud.portal.util.ResponseBase;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * IndexController
 *
 * @author dengtao
 * @since 2021/1/18 下午4:55
 * @version 1.0.0
 */
@Controller
public class IndexController {

	// @Autowired
	// private UserServiceFegin userServiceFegin;

	@RequestMapping("")
	public String index(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String token = CookieUtil.getUid(request, Constants.COOKIE_MEMBER_TOKEN);
		System.out.println(">>>>>>>>>>>>>>> index token: " + token);

		// if (StrUtil.isEmpty(token)) {
		// 	response.sendRedirect("/login");
		// }

		// ResponseBase responseBase = userServiceFegin.findUserByToken(token);
		// ResponseBase responseBase = new ResponseBase();
		// responseBase.setCode(200);
		// LinkedHashMap<String, String> map = new LinkedHashMap<>();
		// map.put("username", "taotao-cloud");
		// responseBase.setData(map);
		//
		// if (!responseBase.getCode().equals(Constants.HTTP_RES_CODE_200)) {
		// 	request.setAttribute("error", "请先登录!");
		// 	return "login";
		// }
		//
		// LinkedHashMap<String, String> linkedHashMap = (LinkedHashMap<String, String>) responseBase.getData();
		// String username = linkedHashMap.get("username");

		// request.setAttribute("username", username);
		return "index";
	}

	@RequestMapping("/login.html")
	public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return "login";
	}
}
