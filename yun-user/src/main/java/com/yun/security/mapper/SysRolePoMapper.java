package com.yun.security.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yun.security.po.SysRolePo;
import com.yun.security.po.SysRolePoExample;
@Mapper
public interface SysRolePoMapper {
    long countByExample(SysRolePoExample example);

    int deleteByExample(SysRolePoExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysRolePo record);

    int insertSelective(SysRolePo record);

    List<SysRolePo> selectByExample(SysRolePoExample example);

    SysRolePo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysRolePo record, @Param("example") SysRolePoExample example);

    int updateByExample(@Param("record") SysRolePo record, @Param("example") SysRolePoExample example);

    int updateByPrimaryKeySelective(SysRolePo record);

    int updateByPrimaryKey(SysRolePo record);
}