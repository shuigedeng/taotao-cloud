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
package com.taotao.cloud.common.base;

/**
 * 常用字符串
 *
 * @author dengtao
 * @version 1.0.0
 * @since 2021/6/22 17:11
 */
public interface StrPool {

	String AMPERSAND = "&";
	String AND = "and";
	String AT = "@";
	String ASTERISK = "*";
	String STAR = "*";
	String BACK_SLASH = "\\";
	String COLON = ":";
	String COMMA = ",";
	String DASH = "-";
	String DOLLAR = "$";
	String DOT = ".";
	String DOTDOT = "..";
	String DOT_CLASS = ".class";
	String DOT_JAVA = ".java";
	String DOT_XML = ".xml";
	String EMPTY = "";
	String EQUALS = "=";
	String FALSE = "false";
	String SLASH = "/";
	String HASH = "#";
	String HAT = "^";
	String LEFT_BRACE = "{";
	String BRACE = "{}";
	String LEFT_BRACKET = "(";
	String LEFT_CHEV = "<";
	String NEWLINE = "\n";
	String N = "n";
	String NO = "no";
	String NULL = "null";
	String OFF = "off";
	String ON = "on";
	String PERCENT = "%";
	String PIPE = "|";
	String PLUS = "+";
	String QUESTION_MARK = "?";
	String EXCLAMATION_MARK = "!";
	String QUOTE = "\"";
	String RETURN = "\r";
	String TAB = "\t";
	String RIGHT_BRACE = "}";
	String RIGHT_BRACKET = ")";
	String RIGHT_CHEV = ">";
	String SEMICOLON = ";";
	String SINGLE_QUOTE = "'";
	String BACKTICK = "`";
	String SPACE = " ";
	String TILDA = "~";
	String LEFT_SQ_BRACKET = "[";
	String RIGHT_SQ_BRACKET = "]";
	String TRUE = "true";
	String UNDERSCORE = "_";
	String UTF_8 = "UTF-8";
	String GBK = "GBK";
	String US_ASCII = "US-ASCII";
	String ISO_8859_1 = "ISO-8859-1";
	String Y = "y";
	String YES = "yes";
	String ONE = "1";
	String ZERO = "0";
	String DOLLAR_LEFT_BRACE = "${";
	String HASH_LEFT_BRACE = "#{";
	String CRLF = "\r\n";
	String HTML_NBSP = "&nbsp;";
	String HTML_AMP = "&amp";
	String HTML_QUOTE = "&quot;";
	String HTML_LT = "&lt;";
	String HTML_GT = "&gt;";
	String STRING_TYPE_NAME = "java.lang.String";
	String LONG_TYPE_NAME = "java.lang.Long";
	String INTEGER_TYPE_NAME = "java.lang.Integer";
	String SHORT_TYPE_NAME = "java.lang.Short";
	String DOUBLE_TYPE_NAME = "java.lang.Double";
	String FLOAT_TYPE_NAME = "java.lang.Float";
	String BOOLEAN_TYPE_NAME = "java.lang.Boolean";
	String SET_TYPE_NAME = "java.lang.Set";
	String LIST_TYPE_NAME = "java.lang.List";
	String COLLECTION_TYPE_NAME = "java.lang.Collection";
	String DATE_TYPE_NAME = "java.util.Date";
	String LOCAL_DATE_TIME_TYPE_NAME = "java.time.LocalDateTime";
	String LOCAL_DATE_TYPE_NAME = "java.time.LocalDate";
	String LOCAL_TIME_TYPE_NAME = "java.time.LocalTime";
	/**
	 * 编码
	 */
	String UTF8 = "UTF-8";
	/**
	 * JSON 资源
	 */
	String CONTENT_TYPE = "application/json; charset=utf-8";


	String TEST_TOKEN = "Bearer test";
	String TEST = "test";
	String PROD = "prod";

	/**
	 * 默认的根节点path
	 */
	String DEF_ROOT_PATH = COMMA;
	/**
	 * 默认的父id
	 */
	Long DEF_PARENT_ID = 0L;

	String UNKNOW = "unknown";
}