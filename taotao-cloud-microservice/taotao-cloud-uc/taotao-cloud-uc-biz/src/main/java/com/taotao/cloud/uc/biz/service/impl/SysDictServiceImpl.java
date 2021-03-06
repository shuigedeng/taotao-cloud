package com.taotao.cloud.uc.biz.service.impl;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.taotao.cloud.common.enums.ResultEnum;
import com.taotao.cloud.common.exception.BusinessException;
import com.taotao.cloud.uc.api.query.dict.DictPageQuery;
import com.taotao.cloud.uc.biz.entity.QSysDict;
import com.taotao.cloud.uc.biz.entity.SysDict;
import com.taotao.cloud.uc.biz.repository.SysDictRepository;
import com.taotao.cloud.uc.biz.service.ISysDictItemService;
import com.taotao.cloud.uc.biz.service.ISysDictService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 字典表 服务实现类
 *
 * @author dengtao
 * @since 2020/4/30 11:19
 */
@Service
@AllArgsConstructor
public class SysDictServiceImpl implements ISysDictService {

	private final SysDictRepository sysDictRepository;
	private final ISysDictItemService sysDictItemService;

	private final QSysDict SYS_DICT = QSysDict.sysDict;
	private final BooleanExpression PREDICATE = SYS_DICT.eq(SYS_DICT);
	private final OrderSpecifier<Integer> SORT_DESC = SYS_DICT.sortNum.desc();
	private final OrderSpecifier<LocalDateTime> CREATE_TIME_DESC = SYS_DICT.createTime
		.desc();

	@Override
	@Transactional(rollbackFor = Exception.class)
	public SysDict save(SysDict sysDict) {
		String dictCode = sysDict.getDictCode();
		if (sysDictRepository.existsByDictCode(dictCode)) {
			throw new BusinessException(ResultEnum.DICT_CODE_REPEAT_ERROR);
		}
		return sysDictRepository.saveAndFlush(sysDict);
	}

	@Override
	public List<SysDict> getAll() {
		return sysDictRepository.findAll();
	}

	@Override
	public Page<SysDict> getPage(Pageable page, DictPageQuery dictPageQuery) {
		Optional.ofNullable(dictPageQuery.getDictName())
			.ifPresent(dictName -> PREDICATE.and(SYS_DICT.dictName.like(dictName)));
		Optional.ofNullable(dictPageQuery.getDictCode())
			.ifPresent(dictCode -> PREDICATE.and(SYS_DICT.dictCode.eq(dictCode)));
		Optional.ofNullable(dictPageQuery.getDescription())
			.ifPresent(description -> PREDICATE.and(SYS_DICT.description.like(description)));
		Optional.ofNullable(dictPageQuery.getRemark())
			.ifPresent(remark -> PREDICATE.and(SYS_DICT.remark.like(remark)));
		return sysDictRepository.findAll(PREDICATE, page, SORT_DESC, CREATE_TIME_DESC);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean removeById(Long id) {
		Optional<SysDict> optionalSysDict = sysDictRepository.findById(id);
		optionalSysDict.orElseThrow(() -> new BusinessException(ResultEnum.DICT_NOT_EXIST));
		sysDictRepository.deleteById(id);
		sysDictItemService.deleteByDictId(id);
		return true;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean deleteByCode(String code) {
		SysDict dict = findByCode(code);
		sysDictRepository.delete(dict);

		Long dictId = dict.getId();
		sysDictItemService.deleteByDictId(dictId);
		return true;
	}

	@Override
	public SysDict findById(Long id) {
		Optional<SysDict> optionalSysDict = sysDictRepository.findById(id);
		return optionalSysDict.orElseThrow(() -> new BusinessException(ResultEnum.DICT_NOT_EXIST));
	}

	@Override
	public SysDict findByCode(String code) {
		Optional<SysDict> optionalSysDict = sysDictRepository.findByCode(code);
		return optionalSysDict.orElseThrow(() -> new BusinessException(ResultEnum.DICT_NOT_EXIST));
	}

	@Override
	public SysDict update(SysDict dict) {
		return sysDictRepository.saveAndFlush(dict);
	}
}
