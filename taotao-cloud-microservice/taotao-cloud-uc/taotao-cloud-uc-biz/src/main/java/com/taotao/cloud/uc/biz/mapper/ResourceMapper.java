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
package com.taotao.cloud.uc.biz.mapper;

import com.taotao.cloud.uc.api.dto.resource.ResourceDTO;
import com.taotao.cloud.uc.api.vo.resource.ResourceVO;
import com.taotao.cloud.uc.biz.entity.SysResource;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author dengtao
 * @since 2020/11/11 16:58
 * @version 1.0.0
 */
@Mapper(builder = @Builder(disableBuilder = true),
	unmappedSourcePolicy = ReportingPolicy.IGNORE,
	unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResourceMapper {

	ResourceMapper INSTANCE = Mappers.getMapper(ResourceMapper.class);

	/**
	 * ResourceDTO转SysResource
	 *
	 * @param resourceDTO resourceDTO
	 * @return com.taotao.cloud.uc.biz.entity.SysResource
	 * @author dengtao
	 * @since 2020/11/11 17:21
	 * @version 1.0.0
	 */
	SysResource resourceDtoToSysResource(ResourceDTO resourceDTO);

	/**
	 * SysResource转ResourceVO
	 *
	 * @param sysResource sysResource
	 * @return com.taotao.cloud.uc.api.vo.resource.ResourceVO
	 * @author dengtao
	 * @since 2020/11/11 17:25
	 * @version 1.0.0
	 */
	ResourceVO sysResourceDtoResourceVo(SysResource sysResource);

	/**
	 * list -> SysResource转ResourceVO
	 *
	 * @param resourceList userList
	 * @return java.util.List<com.taotao.cloud.uc.api.vo.user.UserVO>
	 * @author dengtao
	 * @since 2020/11/11 15:00
	 * @version 1.0.0
	 */
	List<ResourceVO> sysResourceToResourceVo(List<SysResource> resourceList);

	/**
	 * 拷贝 UserDTO 到SysUser
	 *
	 * @param resourceDTO resourceDTO
	 * @param sysResource sysResource
	 * @return void
	 * @author dengtao
	 * @since 2020/11/11 16:59
	 * @version 1.0.0
	 */
	void copyResourceDtoToSysResource(ResourceDTO resourceDTO, @MappingTarget SysResource sysResource);

}
