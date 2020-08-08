package com.may.mall.product.dao;

import com.may.mall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author may
 * @email may@gmail.com
 * @date 2020-07-23 23:09:00
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
