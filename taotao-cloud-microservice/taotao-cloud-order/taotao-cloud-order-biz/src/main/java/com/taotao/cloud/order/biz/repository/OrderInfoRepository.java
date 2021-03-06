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
package com.taotao.cloud.order.biz.repository;

import com.taotao.cloud.data.jpa.repository.BaseJpaRepository;
import com.taotao.cloud.order.biz.entity.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * @author dengtao
 * @since 2020/10/22 12:46
 * @version 1.0.0
 */
@Repository
public class OrderInfoRepository extends BaseJpaRepository<Order, Long> {
    public OrderInfoRepository(EntityManager em) {
        super(Order.class, em);
    }
}
