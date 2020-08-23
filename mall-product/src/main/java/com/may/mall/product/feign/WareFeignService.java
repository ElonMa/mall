package com.may.mall.product.feign;

import com.may.common.utils.R;
import com.may.common.vo.SkuHasStockVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("mall-ware")
public interface WareFeignService {

    /**
     * 1.R 上加泛型
     * 2、直接返回数据
     * 3、自己封装解析结果
     * @param skuIds
     * @return
     */
    @PostMapping("/ware/waresku/hasstock")
    R<List<SkuHasStockVo>> getSkuHasstock(@RequestBody List<Long> skuIds);

}

