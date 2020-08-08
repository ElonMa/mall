package com.may.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.may.common.utils.PageUtils;
import com.may.mall.product.entity.BrandEntity;

import java.util.Map;

/**
 * 品牌
 *
 * @author may
 * @email may@gmail.com
 * @date 2020-07-23 23:09:01
 */
public interface BrandService extends IService<BrandEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

