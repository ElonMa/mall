package com.may.mall.product.service.impl;

import com.may.common.constant.ProductConstant;
import com.may.common.to.es.SkuEsModle;
import com.may.common.utils.R;
import com.may.common.vo.SkuHasStockVo;
import com.may.mall.product.entity.*;
import com.may.mall.product.feign.SearchFeignService;
import com.may.mall.product.feign.WareFeignService;
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

    @Autowired
    WareFeignService wareFeignService;

    @Autowired
    SearchFeignService searchFeignService;

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
        List<Long> skuIdList = skus.stream().map(SkuInfoEntity::getSkuId).collect(Collectors.toList());

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


        //TODO 查询是否有库存
        Map<Long, Boolean> stockMap = null;
        try{
            R<List<SkuHasStockVo>> skuHasstock = wareFeignService.getSkuHasstock(skuIdList);
            stockMap = skuHasstock.getData().stream().collect(Collectors.toMap(SkuHasStockVo::getSkuId, item -> item.getHasStock()));

        }catch (Exception e){
            log.error("库存服务查询异常 ：原因{}",e);
        }


        //封装信息
        Map<Long, Boolean> finalStockMap = stockMap;
        List<SkuEsModle> upProducts = skus.stream().map(sku -> {
            SkuEsModle esModle = new SkuEsModle();
            BeanUtils.copyProperties(sku, esModle);
            esModle.setSkuPrice(sku.getPrice());
            esModle.setSkuImg(sku.getSkuDefaultImg());


            if (finalStockMap == null) {
                esModle.setHasStock(true);
            } else {
                esModle.setHasStock(finalStockMap.get(sku.getSkuId()));
            }


            //TODO 热度评分
            esModle.setHotScore(0L);

            BrandEntity brand = brandService.getById(esModle.getBrandId());
            esModle.setBrandName(brand.getName());
            esModle.setBrandImg(brand.getLogo());

            CategoryEntity category = categoryService.getById(esModle.getCatelogId());
            esModle.setCatelogName(category.getName());

            esModle.setAttrs(attrsList);


            return esModle;
        }).collect(Collectors.toList());


        //TODO 发送 给es
        R r = searchFeignService.productStatusUp(upProducts);
        if(r.getCode()==0){
            //修改 spu 状态
            baseMapper.updateSpuStatus(spuId, ProductConstant.StatusEnum.SPU_UP.getCode());
        }else{
            //远程调用失败
            //TODO 重复调用，接口密等性 重试机制

        }


    }

}