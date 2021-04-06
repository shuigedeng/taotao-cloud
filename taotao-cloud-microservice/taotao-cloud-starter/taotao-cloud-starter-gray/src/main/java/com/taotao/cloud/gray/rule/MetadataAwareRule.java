package com.taotao.cloud.gray.rule;


import com.taotao.cloud.gray.predicate.DiscoveryEnabledPredicate;
import com.taotao.cloud.gray.predicate.MetadataAwarePredicate;

/**
 * 灰度元数据适配规则类
 * @author pangu
 * @since 2020-7-28
 */
public class MetadataAwareRule extends DiscoveryEnabledRule {

    public MetadataAwareRule() {
        this(new MetadataAwarePredicate());
    }

    public MetadataAwareRule(DiscoveryEnabledPredicate predicate) {
        super(predicate);
    }
}
