package com.sam.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sam.dao.UserCustomMapper;
import com.sam.dao.UserMapper;
import com.sam.dao.UserRoleMapper;
import com.sam.entity.User;
import com.sam.entity.UserRole;
import com.sam.entity.dto.UserDto;
import com.sam.entity.dto.UserDto1;

//业务逻辑层
@Service
public class UserServices {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserCustomMapper userCustomMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;

	/**
	 * 根据id获取用户信息
	 * 
	 * @param id
	 * @return
	 * @作者：刘鑫 @描述：
	 */
	public User getUserById(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	/**
	 * 
	 * @param name
	 * @return
	 * @作者：刘鑫
	 * @描述：根据用户名查询用户信息
	 */

	public User getUserByUserName(String name) {
		return userCustomMapper.selectUserByUserName(name);
	}

	/**
	 * 
	 * @param sex
	 * @return
	 * @作者：刘鑫
	 * @描述：根据性别查询用户信息
	 */
	public List<User> getUserBySex(String sex) {
		return userCustomMapper.selectUserBySex(sex);
	}

	/**
	 * 
	 * @param userName
	 * @param password
	 * @return
	 * @作者：刘鑫
	 * @描述：根据用户名和密码查询用户信息
	 */
	public User getUserByUserNameAndPassword(String userName, String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", userName);
		map.put("password", password);
		return userCustomMapper.selectUserByUserNameAndPassword(map);
	}

	/**
	 * 
	 * @param userName
	 * @param password
	 * @param sex
	 * @param age
	 * @param roleId
	 * @return
	 * @作者：刘鑫
	 * @描述：注册
	 */

	@Transactional
	public void register(String userName, String password, String sex, byte age, Integer roleId) {
		int userId = insert(userName, password, sex, age);
		addUserRole(userId, roleId);
	}

	/**
	 * 
	 * 
	 * @param userName
	 * @param password
	 * @param sex
	 * @param age
	 * @return
	 * @作者：刘鑫
	 * @描述：新增用户
	 */
	public int insert(String userName, String password, String sex, byte age) {
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setSex(sex);
		user.setAge(age);
		userMapper.insert(user);
		return user.getId();
	}

	/**
	 * 
	 * @param userId
	 * @param roleId
	 * @作者：刘鑫
	 * @描述：新增用户与角色关系
	 */
	public void addUserRole(Integer userId, Integer roleId) {
		UserRole userRole = new UserRole();
		userRole.setUserId(userId);
		userRole.setRoleId(roleId);
		userRoleMapper.insertSelective(userRole);
	}

	/**
	 * 
	 * @param userId
	 * @param roleId
	 * @作者：刘鑫
	 * @描述:根据主键更新用户信息
	 */

	public int updateByPrimaryKey(Integer id, String userName, String password, String sex, byte age) {
		User user = new User();
		user.setId(id);
		user.setUserName(userName);
		user.setPassword(password);
		user.setSex(sex);
		user.setAge(age);
		return userMapper.updateByPrimaryKey(user);
	}

	public int updateByPrimaryKeySelective(Integer id, String userName, String password, String sex, byte age) {
		User user = new User();
		user.setId(id);
		user.setUserName(userName);
		user.setPassword(password);
		user.setSex(sex);
		user.setAge(age);
		return userMapper.updateByPrimaryKeySelective(user);
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @作者：刘鑫
	 * @描述：根据主键id删除用户信息
	 */

	public int delete(Integer id) {
		return userMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 
	 * @param sex
	 * @return
	 * @作者：刘鑫
	 * @描述：根据性别删除用户信息
	 */
	public int deleteBySex(String sex) {

		return userCustomMapper.deleteBySex(sex);
	}

	/**
	 * 
	 * @描述：根据用户名查询用户的角色名
	 */
	public UserDto getUserAndRoleByUserName(String name) {
		return userCustomMapper.selectUserAndRoleByUserName(name);
	}

	/**
	 * 
	 * @param roleName
	 * @return
	 * @作者：刘鑫
	 * @描述：根据角色名查询用户列表
	 */

	public List<User> selcectUserList(String roleName) {
		return userCustomMapper.selcectUserList(roleName);
	}
	/**
	 * 
	 * @param age
	 * @return
	 * @作者：刘鑫
	 * @描述：根据性别查询用户信息及角色名
	 */
	public UserDto1 selectUserAndRoleByAge(int age){
		return userCustomMapper.selectUserAndRoleByAge(age);
	}
}
