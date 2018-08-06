package com.sam.dao;

import java.util.List;

import java.util.Map;

import com.sam.entity.User;

import com.sam.entity.dto.UserDto;
import com.sam.entity.dto.UserDto1;

public interface UserCustomMapper {
	User selectUserByUserName(String name);

	List<User> selectUserBySex(String sex);

	User selectUserByUserNameAndPassword(Map<String, Object> map);

	int deleteBySex(String sex);

	UserDto selectUserAndRoleByUserName(String name);
	
	List<User> selcectUserList(String roleName);
	
	UserDto1 selectUserAndRoleByAge(int age);
}
