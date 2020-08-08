package com.may.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.may.common.utils.PageUtils;
import com.may.mall.order.entity.OrderEntity;

import java.util.Map;

/**
 * 订单
 *
 * @author may
 * @email may@gmail.com
 * @date 2020-07-24 21:58:52
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

