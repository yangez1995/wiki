package com.yez.wiki.user.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yez.wiki.entity.ResponseMessage;
import com.yez.wiki.entity.user.Role;
import com.yez.wiki.factory.MapFactory;
import com.yez.wiki.factory.RoleFactory;
import com.yez.wiki.user.dao.RoleMessageMapper;
import com.yez.wiki.user.service.IRoleMessageService;
import com.yez.wiki.util.StringUtil;

@Service
public class RoleMessageService implements IRoleMessageService {
	@Autowired
	private RoleMessageMapper roleMapper;
	
	@Override
	public ResponseMessage insert(String name, String describe) {
		//验证角色名
		if(StringUtil.isEmpty(name)) {
			return ResponseMessage.fail("角色名不能为空!");
		}
		if(!StringUtil.checkLength(name, 2, 30)) {
			return ResponseMessage.fail("角色名在2-30个字符之间!");
		}
		if(!StringUtil.isLetterDigitOrChinese(name)) {
			return ResponseMessage.fail("角色名含有非法字符!");
		}
		if(checkRoleNameExist(name)) {
			return ResponseMessage.fail("角色名已经存在!");
		}
		
		//验证角色描述
		if(!StringUtil.checkLength(describe, 0, 200)) {
			return ResponseMessage.fail("角色描述不能超过200个字符!");
		}
		
		Role role = RoleFactory.product(name, describe);
		roleMapper.insert(role);
		return ResponseMessage.success();
	}
	
	@Override
	public ResponseMessage delete(int id) {
		roleMapper.delete(id);
		return ResponseMessage.success();
	}
	
	@Override
	public ResponseMessage update(Role role) {
		roleMapper.update(role);
		return ResponseMessage.success();
	}
	
	@Override
	public ResponseMessage getPage(Map<String, Object> map) {
		return ResponseMessage.success(MapFactory.pageAndSize(roleMapper.getPage(map), roleMapper.getNumber(map)));
	}

	@Override
	public boolean checkRoleNameExist(String name) {
		return roleMapper.checkRoleNameExist(name) > 0 ? true : false;
	}
}
