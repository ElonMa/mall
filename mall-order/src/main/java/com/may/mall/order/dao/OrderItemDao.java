package com.may.mall.order.dao;

import com.may.mall.order.entity.OrderItemEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单项信息
 * 
 * @author may
 * @email may@gmail.com
 * @date 2020-07-24 21:58:52
 */
@Mapper
public interface OrderItemDao extends BaseMapper<OrderItemEntity> {
	
}
