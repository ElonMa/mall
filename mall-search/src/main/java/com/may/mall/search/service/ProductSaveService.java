package com.may.mall.search.service;


import com.may.common.to.es.SkuEsModle;

import java.io.IOException;
import java.util.List;

public interface ProductSaveService {
    boolean productStatusUp(List<SkuEsModle> skuEsModles) throws IOException;
}
