package com.yez.wiki.main.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yez.wiki.entity.wiki.Wiki;

public interface WikiMapper {
	public Wiki getWikiById(@Param("id")int id);
	public void newSimpleWiki(Map<String, Object> map);
}