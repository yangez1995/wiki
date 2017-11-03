package com.yez.wiki.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yez.wiki.entity.user.Role;

public interface RoleMessageMapper {
	/**
	 * 新建角色
	 * @param role 要新建的角色信息
	 */
	public void insert(Role role);
	/**
	 * 删除角色信息
	 */
	public void delete(@Param("id")int id);
	
	/**
	 * 修改角色信息
	 */
	public int update(Role role);
	
	public List<Role> getPage(Map<String, Object> map);
	public int getNumber(Map<String, Object> map);
	
	/**
	 * 检测角色名是否存在
	 * @param name 要确认是否存在的角色名
	 * @return 数据库中与传入角色名相同角色名的数量
	 */
	public int checkRoleNameExist(@Param("name")String name);
}
