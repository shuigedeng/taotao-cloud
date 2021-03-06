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
package com.taotao.cloud.elk;

import com.taotao.cloud.elk.properties.ElkWebAspectProperties;
import com.taotao.cloud.elk.properties.ElkHealthLogStatisticProperties;
import com.taotao.cloud.elk.properties.ElkProperties;
import com.taotao.cloud.elk.properties.ElkWebProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * PropertiesAutoConfiguration
 *
 * @author dengtao
 * @version 1.0.0
 * @since 2020/7/29 14:18
 */
@EnableConfigurationProperties({
	ElkProperties.class,
	ElkWebAspectProperties.class,
	ElkHealthLogStatisticProperties.class,
	ElkWebProperties.class
})
public class PropertiesAutoConfiguration {

}
