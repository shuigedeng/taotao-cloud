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
package com.taotao.cloud.file.configuration;

import com.taotao.cloud.common.utils.LogUtil;
import com.taotao.cloud.file.base.AbstractUploadFile;
import com.taotao.cloud.file.constant.UploadFileConstant;
import com.taotao.cloud.file.exception.UploadFileException;
import com.taotao.cloud.file.pojo.UploadFileInfo;
import com.taotao.cloud.file.propeties.NginxProperties;
import java.io.File;
import java.io.FileInputStream;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author dengtao
 * @version 1.0.0
 * @since 2020/10/26 10:28
 */
@ConditionalOnProperty(
	prefix = UploadFileConstant.BASE_UPLOAD_FILE_PREFIX,
	name = UploadFileConstant.TYPE,
	havingValue = UploadFileConstant.DFS_NGINX
)
public class NginxAutoConfiguration {

	private final NginxProperties properties;

	public NginxAutoConfiguration(NginxProperties properties) {
		super();
		Assert.notNull(properties, "NginxProperties为null");
		this.properties = properties;
	}

	@Bean
	public NginxUploadFile fileUpload() {
		return new NginxUploadFile();
	}

	public class NginxUploadFile extends AbstractUploadFile {

		@Override
		protected UploadFileInfo uploadFile(MultipartFile file, UploadFileInfo uploadFileInfo) {
			try {
				String f = properties.getUploadPath() + "/" + uploadFileInfo.getName();
				f = f.substring(0, f.lastIndexOf("/"));
				File localFile = new File(f);
				try {
					if (!localFile.exists() && !localFile.isDirectory()) {
						localFile.mkdirs();
					}
					FileUtils.copyInputStreamToFile(file.getInputStream(), new File(f));
					// file.transferTo(new File(config.getNginxUploadPath() + path));
				} catch (Exception e) {
					LogUtil.error("[nginx]文件上传错误:", e);
					throw new UploadFileException("[nginx]文件上传错误");
				}
				String s = properties.getDownPath() + "/" + uploadFileInfo.getName();
				uploadFileInfo.setUrl(s);
				return uploadFileInfo;
			} catch (Exception e) {
				LogUtil.error("[nginx]文件上传失败:", e);
				throw new UploadFileException("[nginx]文件上传失败");
			}
		}

		@Override
		protected UploadFileInfo uploadFile(File file, UploadFileInfo uploadFileInfo) {
			try {
				String f = properties.getUploadPath() + "/" + uploadFileInfo.getName();
				f = f.substring(0, f.lastIndexOf("/"));
				File localFile = new File(f);
				try {
					if (!localFile.exists() && !localFile.isDirectory()) {
						localFile.mkdirs();
					}
					FileUtils.copyInputStreamToFile(new FileInputStream(file), new File(f));
					// file.transferTo(new File(config.getNginxUploadPath() + path));
				} catch (Exception e) {
					LogUtil.error("[nginx]文件上传错误:", e);
					throw new UploadFileException("[nginx]文件上传错误");
				}
				String s = properties.getDownPath() + "/" + uploadFileInfo.getName();
				uploadFileInfo.setUrl(s);
				return uploadFileInfo;
			} catch (Exception e) {
				LogUtil.error("[nginx]文件上传失败:", e);
				throw new UploadFileException("[nginx]文件上传失败");
			}
		}

		@Override
		public UploadFileInfo delete(UploadFileInfo uploadFileInfo) {
			try {
				FileUtils
					.forceDelete(new File(properties.getUploadPath() + "/" + uploadFileInfo.getName()));
			} catch (Exception e) {
				LogUtil.error("[nginx]文件删除失败:", e);
				throw new UploadFileException("[nginx]文件删除失败");
			}
			return uploadFileInfo;
		}
	}

}
