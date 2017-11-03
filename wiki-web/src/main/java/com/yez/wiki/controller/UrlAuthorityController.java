package com.yez.wiki.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yez.wiki.user.security.SecurityMetadataSourceImpl;
import com.yez.wiki.user.security.SpringSecuritySessionUtil;

@Controller
public class UrlAuthorityController {
	@Autowired
	private SecurityMetadataSourceImpl securityMetadataSourceImpl;
	
	@ResponseBody
	@RequestMapping(value = "/getElementAuth", method = RequestMethod.POST)
	public Map<String, String> getElementAuth(@RequestBody List<Map<String, String>> elements) {
		Map<String, String> map = new HashMap<String, String>();
		List<String> userAuths = SpringSecuritySessionUtil.getOnLogUserAuths();
		for(Map<String, String> element : elements) {
			String url = element.get("url");
			List<String> resourceAuths = new ArrayList<String>();
			for(ConfigAttribute ca : securityMetadataSourceImpl.getAttributes(url)) {
				resourceAuths.add(ca.getAttribute());
			}
			resourceAuths.retainAll(userAuths);
			if(resourceAuths.size() > 0) {
				map.put(url, "true");
			} else {
				map.put(url, "false");
			}
		}
		return map;
	}
}
