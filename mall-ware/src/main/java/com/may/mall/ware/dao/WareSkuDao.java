package com.may.mall.ware.dao;

import com.may.mall.ware.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品库存
 * 
 * @author may
 * @email may@gmail.com
 * @date 2020-07-24 22:08:25
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {
	
}
