package com.may.mall.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * Nacos 做配置中心
 * 1、引入依赖
 * 2、创建bootstrape。properties
 * spring.application.name= mall-coupon
 * spring.cloud.nacos.server-addr=127.0.0.1:8848
 * 3、配置中心添加数据集dataid
 * 动态获取配置
 * @RefreshScope
 * @value
 * 如果配置中心和当前应用中都配置了相同的项，优先使用配置中心
 *2、细节
 * 命名空间 用来做配置隔离的
 * 默认：public
 * spring.cloud.nacos.config.namespace=4cd60446-ce42-44f3-bd3e-5c9624a08ec8 指定命名空间
 * 配置集 文件
 * 配置分组 spring.cloud.nacos.config.group=
 *
 */

@EnableDiscoveryClient
@SpringBootApplication
public class MallCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallCouponApplication.class, args);
    }

}
