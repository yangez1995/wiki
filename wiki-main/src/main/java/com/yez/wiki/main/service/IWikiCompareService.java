package com.yez.wiki.main.service;

import java.util.Map;

import com.yez.wiki.entity.ResponseMessage;

public interface IWikiCompareService {
	public ResponseMessage card(Map<String, Object> map);
	public ResponseMessage label(Map<String, Object> map);
	public ResponseMessage catal(Map<String, Object> map);
	public ResponseMessage chapter(Map<String, Object> map);
}
