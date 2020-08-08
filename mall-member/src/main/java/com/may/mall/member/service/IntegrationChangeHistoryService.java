package com.may.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.may.common.utils.PageUtils;
import com.may.mall.member.entity.IntegrationChangeHistoryEntity;

import java.util.Map;

/**
 * 积分变化历史记录
 *
 * @author may
 * @email may@gmail.com
 * @date 2020-07-24 21:54:29
 */
public interface IntegrationChangeHistoryService extends IService<IntegrationChangeHistoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

