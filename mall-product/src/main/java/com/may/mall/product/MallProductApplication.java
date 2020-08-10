package com.may.mall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 逻辑删除
 *1)配置全局逻辑删除的规则（省略）
 * 2）配置逻辑删除的组件bean（省略）
 * 3）给bean实体类加上逻辑删除注解
 */
@MapperScan("com.may.mall.product.dao")
@EnableDiscoveryClient
@SpringBootApplication
public class MallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallProductApplication.class, args);
    }

}
