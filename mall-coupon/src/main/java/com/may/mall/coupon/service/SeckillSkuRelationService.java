package com.may.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.may.common.utils.PageUtils;
import com.may.mall.coupon.entity.SeckillSkuRelationEntity;

import java.util.Map;

/**
 * 秒杀活动商品关联
 *
 * @author may
 * @email may@gmail.com
 * @date 2020-07-24 21:48:41
 */
public interface SeckillSkuRelationService extends IService<SeckillSkuRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

