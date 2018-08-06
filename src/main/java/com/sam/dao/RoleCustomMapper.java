package com.sam.dao;

import java.util.List;

import com.sam.entity.Role;

public interface RoleCustomMapper {
	
	    Role selectByRoleName(String name);

	    int deleteByRoleName(String name);
	    
	    List<Role> selectRoleList();
}
