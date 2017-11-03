package com.yez.wiki.user.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;

import com.yez.wiki.entity.security.GrantedAuthorityImpl;
import com.yez.wiki.entity.security.UserDetailsImpl;
import com.yez.wiki.exception.NoUserLoginException;

public class SpringSecuritySessionUtil {
	public static String getOnLogUsername() throws NoUserLoginException {
		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
			throw new NoUserLoginException();
		}
		return ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
	}
	
	public static int getOnLogUserId() throws NoUserLoginException {
		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
			throw new NoUserLoginException();
		}
		return ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
	}
	
	public static int getOnLogUserIdWithOutException(){
		return ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
	}
	
	@SuppressWarnings("unchecked")
	public static List<String> getOnLogUserAuths(){
		UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<GrantedAuthorityImpl> list = (List<GrantedAuthorityImpl>) user.getAuthorities();
		List<String> auths = new ArrayList<String>();
		for(GrantedAuthorityImpl g : list) {
			auths.add(g.getAuthority());
		}
		return auths;
	}
}
