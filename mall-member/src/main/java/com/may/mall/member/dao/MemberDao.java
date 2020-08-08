package com.may.mall.member.dao;

import com.may.mall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author may
 * @email may@gmail.com
 * @date 2020-07-24 21:54:29
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
