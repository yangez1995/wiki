package com.yez.wiki.user.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yez.wiki.entity.ResponseMessage;
import com.yez.wiki.entity.user.OneToMoreIds;
import com.yez.wiki.factory.MapFactory;
import com.yez.wiki.user.config.RoleConfig;
import com.yez.wiki.user.dao.UserRoleMapper;
import com.yez.wiki.user.security.SpringSecuritySessionUtil;
import com.yez.wiki.user.service.IAdminRoleService;
import com.yez.wiki.user.service.IUserRoleService;
@Service
public class UserRoleService implements IUserRoleService {
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private IAdminRoleService adminRoleService;
	
	@Override
	public ResponseMessage update(OneToMoreIds ids) {
		if(!adminRoleService.isHasAuthority(SpringSecuritySessionUtil.getOnLogUserIdWithOutException(), ids.getId())) {
			return ResponseMessage.warning("你的级别不足以修改该用户角色！");
		}
		if(!adminRoleService.isHasSetAuthority(SpringSecuritySessionUtil.getOnLogUserIdWithOutException(), ids.getIds())) {
			return ResponseMessage.warning("不能赋予用户高于或等于你等级的角色！");
		}
		if(ids.getIds().isEmpty()) {
			userRoleMapper.deleteAllRoles(ids.getId());
		} else {
			List<Integer> nowList = userRoleMapper.getRolesId(ids.getId());
			if(nowList.isEmpty()) {
				Map<String, Object> insertMap = MapFactory.oneToMoreIdsMap(ids);
				userRoleMapper.addRoles(insertMap);
			} else {
				Map<String, Object> deleteMap = MapFactory.oneToMoreIdsMap(ids);
				userRoleMapper.deleteRoles(deleteMap);
				ids.getIds().removeAll(nowList);
				if(!ids.getIds().isEmpty()) {
					Map<String, Object> insertMap = MapFactory.oneToMoreIdsMap(ids);
					userRoleMapper.addRoles(insertMap);
				}
			}
		}
		return ResponseMessage.success();
	}
	
	@Override
	public ResponseMessage getPage(Map<String, Object> map) {
		return ResponseMessage.success(MapFactory.pageAndSize(userRoleMapper.getPage(map), userRoleMapper.getNumber(map)));
	}

	@Override
	public ResponseMessage getOtherRoles(List<Integer> list) {
		List<Map<String, Object>> resultList = new LinkedList<Map<String, Object>>();
		resultList = list.isEmpty() ? getRoles() : userRoleMapper.getOtherRoles(list);
		for(int i = 0; i < resultList.size(); i++) {
			if(((int) resultList.get(i).get("value")) == RoleConfig.SUPER_ADMIN_ROLE_ID) {
				resultList.remove(i);
				break;
			}
		}
		return ResponseMessage.success(resultList);
	}

	@Override
	public List<Map<String, Object>> getRoles() {
		return userRoleMapper.getRoles();
	}
}
