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
package com.taotao.cloud.demo.graphql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableKafka
public class TaotaoCloudDemoKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaotaoCloudDemoKafkaApplication.class, args);
	}

//	@KafkaListener(topics = "access")
//	public void onMessage(ConsumerRecord<String, String> record) {
//		String value = record.value();
//		if (value.length() % 2 == 0) {
//			throw new RuntimeException("模拟业务出错");
//		}
//	}

}
