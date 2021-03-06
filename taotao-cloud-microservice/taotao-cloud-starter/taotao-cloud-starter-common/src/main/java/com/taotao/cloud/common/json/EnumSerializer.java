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
package com.taotao.cloud.common.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.taotao.cloud.common.enums.BaseEnum;
import java.io.IOException;

/**
 * 继承了BaseEnum接口的枚举值，将会统一按照以下格式序列化 { "code": "XX", "desc": "xxx" }
 *
 * @author dengtao
 * @version 1.0.0
 * @since 2020/5/2 16:45
 */
public class EnumSerializer extends StdSerializer<BaseEnum> {

	public static final EnumSerializer INSTANCE = new EnumSerializer();
	public static final String ALL_ENUM_KEY_FIELD = "code";
	public static final String ALL_ENUM_DESC_FIELD = "desc";

	public EnumSerializer() {
		super(BaseEnum.class);
	}

	@Override
	public void serialize(BaseEnum distance, JsonGenerator generator, SerializerProvider provider)
		throws IOException {
		generator.writeStartObject();
		generator.writeFieldName(ALL_ENUM_KEY_FIELD);
		generator.writeString(distance.getCode().toString());
		generator.writeFieldName(ALL_ENUM_DESC_FIELD);
		generator.writeEndObject();
	}
}
