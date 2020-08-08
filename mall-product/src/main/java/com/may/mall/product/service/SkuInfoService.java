package com.may.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.may.common.utils.PageUtils;
import com.may.mall.product.entity.SkuInfoEntity;

import java.util.Map;

/**
 * sku信息
 *
 * @author may
 * @email may@gmail.com
 * @date 2020-07-23 23:58:51
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

