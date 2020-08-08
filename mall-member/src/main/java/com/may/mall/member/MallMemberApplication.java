package com.may.mall.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * 1、远程调用
 * 1）引入openfein
 * 2）编写接口，告诉springcloud这个接口需要调用远程服务
 *      1、声明每一个方法调用哪个远程服务请求
 * 3}开启远程调用功能
 */
@EnableFeignClients(basePackages = "com.may.mall.member.feign")
@EnableDiscoveryClient
@SpringBootApplication
public class MallMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallMemberApplication.class, args);
    }

}
