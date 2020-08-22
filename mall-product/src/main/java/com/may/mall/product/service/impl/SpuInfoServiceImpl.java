package com.may.mall.product.service.impl;

import com.may.common.to.es.SkuEsModle;
import com.may.mall.product.entity.*;
import com.may.mall.product.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.may.common.utils.PageUtils;
import com.may.common.utils.Query;

import com.may.mall.product.dao.SpuInfoDao;


@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {

    @Autowired
    SkuInfoService skuInfoService;

    @Autowired
    BrandService brandService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    AttrService attrService;

    @Autowired
    ProductAttrValueService productAttrValueService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                new QueryWrapper<SpuInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void up(Long spuId) {
        //查询当前spuid对应的 所有sku信息，品牌的名字；
        List<SkuInfoEntity> skus =   skuInfoService.getSkusBySpuiId(spuId);

        // TODO 查询当前sku的所有可以被检索的规格属性；

       List<ProductAttrValueEntity> baseAttrs = productAttrValueService.baseAttrlistforspu(spuId);
        List<Long> attrIds = baseAttrs.stream().map(attr -> {
            return attr.getAttrId();
        }).collect(Collectors.toList());

        List<Long> searchAttrIds = attrService.selectSearchAttrIds(attrIds);

        Set<Long> idSet = new HashSet<Long>(searchAttrIds);

        List<SkuEsModle.Attrs> attrsList = baseAttrs.stream().filter(item ->{
            return idSet.contains( item.getAttrId());
        }).map(item ->{
            SkuEsModle.Attrs attr = new SkuEsModle.Attrs();
            BeanUtils.copyProperties(item,attr);
            return attr;
        }).collect(Collectors.toList());

        List<SkuEsModle> uoProducts = new ArrayList<>();





         //封装信息
        skus.stream().map(sku ->{
            SkuEsModle esModle = new SkuEsModle();
            BeanUtils.copyProperties(sku,esModle);
            esModle.setSkuPrice(sku.getPrice());
            esModle.setSkuImg(sku.getSkuDefaultImg());
            //TODO 查询是否有库存
            //TODO 热度评分
            esModle.setHotScore(0L);

            BrandEntity brand = brandService.getById(esModle.getBrandId());
            esModle.setBrandName(brand.getName());
            esModle.setBrandImg(brand.getLogo());

            CategoryEntity category = categoryService.getById(esModle.getCatelogId());
            esModle.setCatelogName(category.getName());

            esModle.setAttrs(attrsList);



            return  esModle;
        } ).collect(Collectors.toList());

        //TODO 发送 给es


    }

}