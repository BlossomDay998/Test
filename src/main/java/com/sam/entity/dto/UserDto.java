package com.sam.entity.dto;

/**
 * 
 * @作者：刘鑫
 * @专业：移动应用开发
 * @修改日期：2018/7/29
 * @描述:用户自定义对象
 *
 */
public class UserDto {
	private Integer userId;// 用户ID

	private String userName;// 用户名
	
	private int age;// 年龄
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	private String roleName;// 角色名

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	

}
