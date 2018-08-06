package com.sam.controller;

import java.util.List;

import org.springframework.util.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.sam.entity.User;
import com.sam.entity.dto.UserDto1;
import com.sam.service.UserServices;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/user")
@Api(value = "user")
public class UserController {
	private static final Logger LOG = Logger.getLogger(UserController.class);

	@Autowired
	private UserServices userServices;

	@ApiOperation(value = "根据ID查询用户")
	@RequestMapping(value = "/getUser",method=RequestMethod.GET)
	@ResponseBody
	public JSONObject getUser(
			@ApiParam(required = true, value = "用户ID", name = "id") @RequestParam(required = true, value = "id") Integer id) {
		User user = userServices.getUserById(id);
		JSONObject obj = new JSONObject();
		obj.put("user", user);
		return obj;
	}
	
	@ApiOperation(value = "根据name查询用户")
	@RequestMapping(value = "/getUserByName",method = RequestMethod.GET)
	public JSONObject getUserByName(
			@ApiParam(required = true, value = "用户名", name = "name") @RequestParam(required = true, value = "name") String name){
		User user = userServices.getUserByUserName(name);
		JSONObject obj = new JSONObject();
		obj.put("user", user);
		return obj;
	}
	
	@ApiOperation(value = "根据sex查询用户")
	@RequestMapping(value = "/getUsersBySex",method = RequestMethod.GET)
	public JSONObject getUserBySex(
			@ApiParam(required = true, value = "性别", name = "sex") @RequestParam(required = true, value = "sex") String sex) {
		List<User> userList = userServices.getUserBySex(sex);
		JSONObject obj = new JSONObject();
		obj.put("userList", userList);
		return obj;
	}
	
	@ApiOperation(value = "根据用户名和密码查询用户")
	@RequestMapping(value = "/getUserByNameAndPassword",method = RequestMethod.GET)
	public JSONObject getUserByNameAndPassword(
			@ApiParam(required = true, value = "用户名", name = "userName") @RequestParam(required = true, value = "userName") String userName,
			@ApiParam(required = true, value = "密码", name = "password") @RequestParam(required = true, value = "password")String password) {
		User user = userServices.getUserByUserNameAndPassword(userName, password);
		JSONObject obj = new JSONObject();
		obj.put("user", user);
		return obj;
	}

	@ApiOperation(value = "根据用户名及密码注册用户")
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public JSONObject login(
			@ApiParam(required = true, value = "用户名", name = "userName") @RequestParam(required = true, value = "userName") String userName,
			@ApiParam(required = true, value = "密码", name = "password") @RequestParam(required = true, value = "password") String password) {
		User user = userServices.getUserByUserNameAndPassword(userName, password) ;
		JSONObject obj = new JSONObject();
		if (StringUtils.isEmpty(userName)) {
			obj.put("code", 99999);
			obj.put("msg", "用户名不能为空");
			return obj;
		}
		if (StringUtils.isEmpty(password)) {
			obj.put("code", 99999);
			obj.put("msg", "密码不能为空");
			return obj;
		}

		//User user = userServices.getUserByUserName(userName);
		if (user == null) {
			obj.put("code", 99998);
			obj.put("msg", "用户名不存在");
			return obj;
		}
		if (!user.getPassword().equals(password)) {
			obj.put("code", 99998);
			obj.put("msg", "密码错误");
			return obj;
		}
		obj.put("code", 200);
		obj.put("msg", "登录成功");
		return obj;
	}

	@ApiOperation(value = "注册用户")
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public JSONObject register(
			@ApiParam(required = true, value = "用户名", name = "userName") @RequestParam(required = true, value = "userName") String userName,
			@ApiParam(required = true, value = "密码", name = "password") @RequestParam(required = true, value = "password") String password,
			@ApiParam(required = true, value = "性别", name = "sex") @RequestParam(required = true, value = "sex") String sex,
			@ApiParam(required = true, value = "年龄", name = "age") @RequestParam(required = true, value = "age") byte age,
			@ApiParam(required = true, value = "角色ID", name = "roleId") @RequestParam(required = true, value = "roleId") Integer roleId ) {
		JSONObject obj = new JSONObject();
		try {
			User user = userServices.getUserByUserName(userName);
			if (user != null) {
				obj.put("code", -1);
				obj.put("msg", "用户名已存在");
				return obj;
			}
			userServices.register(userName, password, sex, age, roleId);
			obj.put("code", 200);
			obj.put("msg", "新增用户成功");
		} catch (Exception e) {
			LOG.error("register异常", e);
			obj.put("code", -1);
			obj.put("msg", "新增用户失败");
		}
		return obj;
	}

	@ApiOperation(value = "通过主键更新用户信息")
	@RequestMapping(value="/updateByPrimaryKey",method = RequestMethod.PUT)
	public JSONObject updateByPrimaryKey(
			@ApiParam(required = true, value = "用户ID", name = "id") @RequestParam(required = true, value = "id") Integer id,
			@ApiParam(required = true, value = "用户名", name = "userName") @RequestParam(required = true, value = "userName") String userName,
			@ApiParam(required = true, value = "密码", name = "password") @RequestParam(required = true, value = "password") String password,
			@ApiParam(required = true, value = "性别", name = "sex") @RequestParam(required = true, value = "sex") String sex, 
			@ApiParam(required = true, value = "年龄", name = "age") @RequestParam(required = true, value = "age") byte age) {
		int result = userServices.updateByPrimaryKey(id, userName, password, sex, age);
		JSONObject obj = new JSONObject();
		if (result == 1) {
			obj.put("code", 200);
			obj.put("msg", "更新用户成功");
		} else {
			obj.put("code", -1);
			obj.put("msg", "更新用户失败");
		}

		return obj;
	}
	
	@ApiOperation(value = "选择性通过主键更新用户信息")
	@RequestMapping(value="/updateByPrimaryKeySelective",method = RequestMethod.PUT)
	public JSONObject updateByPrimaryKeySelective(
			@ApiParam(required = true, value = "用户ID", name = "id") @RequestParam(required = true, value = "id") Integer id,
			@ApiParam(required = true, value = "用户名", name = "userName") @RequestParam(required = true, value = "userName") String userName,
			@ApiParam(required = true, value = "密码", name = "password") @RequestParam(required = true, value = "password") String password,
			@ApiParam(required = true, value = "性别", name = "sex") @RequestParam(required = true, value = "sex") String sex, 
			@ApiParam(required = true, value = "年龄", name = "age") @RequestParam(required = true, value = "age") byte age){
		int result = userServices.updateByPrimaryKeySelective(id, userName, password, sex, age);
		JSONObject obj = new JSONObject();
		if (result == 1) {
			obj.put("code", 200);
			obj.put("msg", "【不为空的字段】更新用户成功");
		} else {
			obj.put("code", -1);
			obj.put("msg", "【不为空的字段】更新用户失败");
		}

		return obj;
	}

	@ApiOperation(value = "通过id删除用户信息")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public JSONObject delete(
			@ApiParam(required = true, value = "用户ID", name = "id") @RequestParam(required = true, value = "id") Integer id) {
		int result = userServices.delete(id);
		JSONObject obj = new JSONObject();
		if (result == 1) {
			obj.put("code", 200);
			obj.put("msg", "删除用户成功");
		} else {
			obj.put("code", -1);
			obj.put("msg", "删除用户失败");
		}

		return obj;
	}
	
	@ApiOperation(value = "通过sex删除用户信息")
	@RequestMapping(value="/deleteBySex",method = RequestMethod.DELETE)
	public JSONObject delete(@ApiParam(required = true, value = "性别", name = "sex") @RequestParam(required = true, value = "sex") String sex) {
		int result = userServices.deleteBySex(sex);
		JSONObject obj = new JSONObject();
		if (result == 1) {
			obj.put("code", 200);
			obj.put("msg", "删除用户成功");
		} else {
			obj.put("code", -1);
			obj.put("msg", "删除用户失败");
		}

		return obj;
	}

	@ApiOperation(value = "获取用户列表")
	@RequestMapping(value="/getUserList",method = RequestMethod.GET)
	public JSONObject getUserList(
			@ApiParam(required = true, value = "角色名", name = "roleName") @RequestParam(required = true, value = "roleName") String roleName) {
		List<User> userList = userServices.selcectUserList(roleName);
		JSONObject obj = new JSONObject();
		obj.put("code", 200);
		obj.put("userList", userList);
		return obj;

	}
	@ApiOperation(value = "根据年龄获取用户 和角色信息")
	@RequestMapping(value="/getUserAndRoleByAge",method = RequestMethod.GET)
	public JSONObject getUserAndRoleByAge(@ApiParam(required = true, value = "年龄", name = "age") @RequestParam(required = true, value = "age") int age) {
		UserDto1 userDto1 = userServices.selectUserAndRoleByAge(age);
		JSONObject obj = new JSONObject();
		obj.put("code", 200);
		obj.put("userDto1", userDto1);
		return obj;
	}

}
