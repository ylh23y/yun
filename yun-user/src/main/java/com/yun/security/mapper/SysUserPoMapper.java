package com.yun.security.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yun.security.po.SysUserPo;
import com.yun.security.po.SysUserPoExample;
@Mapper
public interface SysUserPoMapper {
    long countByExample(SysUserPoExample example);

    int deleteByExample(SysUserPoExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysUserPo record);

    int insertSelective(SysUserPo record);

    List<SysUserPo> selectByExample(SysUserPoExample example);

    SysUserPo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysUserPo record, @Param("example") SysUserPoExample example);

    int updateByExample(@Param("record") SysUserPo record, @Param("example") SysUserPoExample example);

    int updateByPrimaryKeySelective(SysUserPo record);

    int updateByPrimaryKey(SysUserPo record);
}