package com.yez.wiki.user.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yez.wiki.entity.ResponseMessage;
import com.yez.wiki.user.dao.UserAccountMapper;
import com.yez.wiki.user.service.IUserAccountService;
import com.yez.wiki.util.PageUtil;
/**
 * 用户帐号Service接口实现类
 * @author 杨恩哲
 * @see IUserAccountService
 * @since V1.0
 */
@Service
public class UserAccountService implements IUserAccountService {
	/**
	 * 用户帐号Mapper
	 */
	@Autowired
	private UserAccountMapper userAccountMapper;

	/*-- UPDATE --*/
	/**
	 * 锁定用户帐号，成功则返回响应码{@code 200}
	 * @param id 要锁定的用户id
	 * @return 响应信息
	 */
	@Override
	public ResponseMessage lock(int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("locked", true);
		userAccountMapper.updateLocked(map);
		return ResponseMessage.success();
	}

	/**
	 * 解锁用户帐号，成功则返回响应码{@code 200}
	 * @param id 要锁定的用户id
	 * @return 响应信息
	 */
	@Override
	public ResponseMessage unlock(int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("locked", false);
		userAccountMapper.updateLocked(map);
		return ResponseMessage.success();
	}
	
	/*-- SELECT --*/
	/**
	 * 获取一页用户帐号信息，成功则返回响应码{@code 200}、用户帐号信息集合
	 * @param map 参数集合，包含：
	 *     int startindex 起始条数
	 *     int pageSize 每页最多显示多少条信息
	 *     int id 按id精确搜索，可以为空
	 *     String username 按username模糊搜索，可以为空
	 *     String locked 按locked精确搜索，可以为空
	 * @return 响应信息
	 */
	@Override
	public ResponseMessage getPage(Map<String, Object> map) {
		return ResponseMessage.success(userAccountMapper.getPage(map));
	}

	/**
	 * 获取用户帐号数量，成功则返回响应码{@code 200}、用户帐号数量
	 * @param map 参数集合，包含：
	 *     int id 按id精确搜索，可以为空
	 *     String username 按username模糊搜索，可以为空
	 *     String locked 按locked精确搜索，可以为空
	 * @return 响应信息
	 */
	@Override
	public ResponseMessage getNumber(Map<String, Object> map) {
		return ResponseMessage.success(PageUtil.numberToPage(userAccountMapper.getNumber(map), 10));
	}
}
