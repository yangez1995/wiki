package com.yez.wiki.user.service;

import java.util.List;

public interface IAdminRoleService {
	public boolean isHasAuthority(int onLoginId, int updateId);
	public boolean isHasSetAuthority(int onLoginId, int roleId);
	public boolean isHasSetAuthority(int onLoginId, List<Integer> roleIds);
	public boolean isSuperAdmin(int userId);
}
