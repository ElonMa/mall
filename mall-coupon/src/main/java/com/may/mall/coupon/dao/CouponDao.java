package com.may.mall.coupon.dao;

import com.may.mall.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author may
 * @email may@gmail.com
 * @date 2020-07-24 21:48:41
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
