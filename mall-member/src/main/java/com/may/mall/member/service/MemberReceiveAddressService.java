package com.may.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.may.common.utils.PageUtils;
import com.may.mall.member.entity.MemberReceiveAddressEntity;

import java.util.Map;

/**
 * 会员收货地址
 *
 * @author may
 * @email may@gmail.com
 * @date 2020-07-24 21:54:29
 */
public interface MemberReceiveAddressService extends IService<MemberReceiveAddressEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

