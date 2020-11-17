package com.may.mall.search.controller;


import com.may.common.exception.BizCodeEnume;
import com.may.common.to.es.SkuEsModle;
import com.may.common.utils.R;
import com.may.mall.search.service.ProductSaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/search/save")
@RestController
public class ElasticSaveController {
    @Autowired
    ProductSaveService productSaveService;
    //上架商品
    @PostMapping("/product")
    public R productStatusUp(@RequestBody List<SkuEsModle> skuEsModles){
        boolean b= false;


        try {
            b=productSaveService.productStatusUp(skuEsModles);
        }catch (Exception e){
            log.error("商品上架错误：{}",e);
            return R.error(BizCodeEnume.PRODUCT_UP_EXCEPTION.getCode(),BizCodeEnume.PRODUCT_UP_EXCEPTION.getMsg());
        }
        if (b){
            return R.ok();
        }else{
            return  R.error(BizCodeEnume.PRODUCT_UP_EXCEPTION.getCode(),BizCodeEnume.PRODUCT_UP_EXCEPTION.getMsg());
        }


    }

    @GetMapping("/get")
    public R baseAttrlistforspu(){
        return R.ok().put("data",1);
    }


    
}
