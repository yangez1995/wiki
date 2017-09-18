package com.yez.wiki.user.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.yez.wiki.entity.security.SecurityResources;
import com.yez.wiki.user.service.ISecurityResourcesService;

public class SecurityMetadataSourceImpl implements FilterInvocationSecurityMetadataSource {
	private ISecurityResourcesService securityResourcesService;
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
	
	public SecurityMetadataSourceImpl(ISecurityResourcesService securityResourcesService) {
		this.securityResourcesService = securityResourcesService;
		loadResourceDefine();
	}
	
    private void loadResourceDefine() {
    	resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
    	List<SecurityResources> resources = securityResourcesService.getAllResources();
    	for(SecurityResources resource : resources) {
    		Collection<ConfigAttribute> attrs = new ArrayList<ConfigAttribute>();
    		List<String> authorities = securityResourcesService.loadAuthoritiesByResource(resource.getId());
    		for(String authority : authorities) {
    			ConfigAttribute attr = new SecurityConfig(authority);
    			attrs.add(attr);
    		}
    		attrs.add(new SecurityConfig("SUPER_ADMIN"));
    		resourceMap.put(resource.getUrl(), attrs);
    	}
    }
	
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		String url = ((FilterInvocation) object).getRequestUrl();
		//判断url是否有？，如果有截取？之前url
		int firstQuestionMarkIndex = url.indexOf("?");
        if (firstQuestionMarkIndex != -1) {
            url = url.substring(0, firstQuestionMarkIndex);
        }
        Iterator<String> ite = resourceMap.keySet().iterator();
        while(ite.hasNext()) {
        	String resURL = ite.next();
        	if(url.equals(resURL)) {
        		return resourceMap.get(resURL);
        	}
        }
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}