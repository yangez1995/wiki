package com.yez.wiki.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yez.wiki.entity.user.UserRole;

public interface UserRoleMapper {
	public void addRoles(Map<String, Object> map);
	public void deleteRoles(Map<String, Object> map);
	public void deleteAllRoles(@Param("userId")int userId);
	public List<UserRole> getPage(Map<String, Object> map);
	public int getNumber(Map<String, Object> map);
	public List<Map<String, Object>> getOtherRoles(List<Integer> list);
	public List<Map<String, Object>> getRoles();
	public List<Integer> getRolesId(@Param("userId")int userId);
}
