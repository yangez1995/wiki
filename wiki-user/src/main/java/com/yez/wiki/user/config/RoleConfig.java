package com.yez.wiki.user.config;

import java.io.IOException;
import java.util.Properties;

public class RoleConfig {
	private static final String ROLE_PROPERTIES = "role.properties";
	
	public static int SUPER_ADMIN_ROLE_ID;
	public static int SYS_ADMIN_SENIOR_ROLE_ID;
	public static int SYS_ADMIN_ROLE_ID;
	
	static {
		try {
			Properties properties = new Properties();
			properties.load(RoleConfig.class.getResourceAsStream("/" + ROLE_PROPERTIES));
			SUPER_ADMIN_ROLE_ID = Integer.parseInt(properties.getProperty("superAdminRoleId"));
			SYS_ADMIN_SENIOR_ROLE_ID = Integer.parseInt(properties.getProperty("sysAdminSeniorRoleId"));
			SYS_ADMIN_ROLE_ID = Integer.parseInt(properties.getProperty("sysAdminRoleId"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
