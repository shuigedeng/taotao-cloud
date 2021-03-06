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
package com.taotao.cloud.core.runner;

import static com.taotao.cloud.common.base.CoreProperties.SpringApplicationName;

import com.taotao.cloud.common.utils.LogUtil;
import com.taotao.cloud.common.utils.PropertyUtil;
import org.springframework.boot.CommandLineRunner;

/**
 * CoreCommandLineRunner
 *
 * @author dengtao
 * @version 1.0.0
 * @since 2021/06/22 10:53
 */
public class CoreCommandLineRunner implements CommandLineRunner {

	@Override
	public void run(String... args) {
		String strArgs = String.join("|", args);
		LogUtil.info(PropertyUtil.getProperty(SpringApplicationName)
			+ "-- started with arguments length: {0}, args: {1}", args.length, strArgs);
	}
}
