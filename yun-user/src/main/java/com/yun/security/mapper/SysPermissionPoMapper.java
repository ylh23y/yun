package com.yun.security.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yun.security.po.SysPermissionPo;
import com.yun.security.po.SysPermissionPoExample;
@Mapper
public interface SysPermissionPoMapper {
    long countByExample(SysPermissionPoExample example);

    int deleteByExample(SysPermissionPoExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysPermissionPo record);

    int insertSelective(SysPermissionPo record);

    List<SysPermissionPo> selectByExample(SysPermissionPoExample example);

    SysPermissionPo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysPermissionPo record, @Param("example") SysPermissionPoExample example);

    int updateByExample(@Param("record") SysPermissionPo record, @Param("example") SysPermissionPoExample example);

    int updateByPrimaryKeySelective(SysPermissionPo record);

    int updateByPrimaryKey(SysPermissionPo record);
}