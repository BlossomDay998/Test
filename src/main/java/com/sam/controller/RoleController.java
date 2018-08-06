package com.sam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.sam.entity.Role;
import com.sam.service.RoleServices;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;



@RestController
@RequestMapping(value = "/role")
public class RoleController {
	@Autowired
	private RoleServices roleServices;
    
	@ApiOperation(value = "根据ID查询角色")
	@RequestMapping(value="/getRole",method=RequestMethod.GET)
	@ResponseBody
	public JSONObject getRole(
			@ApiParam(required = true, value = "角色ID", name = "id") @RequestParam(required = true, value = "id") Integer id) {
		Role role = roleServices.getRoleById(id);
		JSONObject obj = new JSONObject();
		obj.put("role", role);
		return obj;
	}
    
	@ApiOperation(value = "根据角色名查询角色")
	@RequestMapping(value = "/getRoleByName",method=RequestMethod.GET)
	public JSONObject getRoleByName(
			@ApiParam(required = true, value = "角色名", name = "roleName") @RequestParam(required = true, value = "roleName")String roleName) {
		Role role = roleServices.getRoleByRoleName(roleName);
		JSONObject obj = new JSONObject();
		obj.put("role", role);
		return obj;
	}
	
	@ApiOperation(value = "通过角色名新增角色")
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public JSONObject insert(
			@ApiParam(required = true, value = "角色名", name = "roleName") @RequestParam(required = true, value = "roleName")String roleName) {
		int result = roleServices.insert(roleName);
		JSONObject obj = new JSONObject();
		if (result == 1) {
			obj.put("code", 200);
			obj.put("msg", "新增用户成功");
		} else {
			obj.put("code", -1);
			obj.put("msg", "新增用户失败");
		}

		return obj;
	}
	
	@ApiOperation(value="通过主键更新角色信息")
	@RequestMapping(value="/updateByPrimaryKey",method=RequestMethod.PUT)
	public JSONObject updateByPrimaryKey(
			@ApiParam(required = true, value = "角色ID", name = "id") @RequestParam(required = true, value = "id") Integer id,
			@ApiParam(required = true, value = "角色名", name = "roleName") @RequestParam(required = true, value = "roleName")String roleName) {
		int result = roleServices.updateByPrimaryKey(id, roleName);
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

	@ApiOperation(value="通过主键删除角色信息")
	@RequestMapping(value="/delete",method=RequestMethod.DELETE)
	public JSONObject delete(
			@ApiParam(required = true, value = "角色ID", name = "id") @RequestParam(required = true, value = "id") Integer id) {
		int result = roleServices.delete(id);
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
	
	@ApiOperation(value="获取角色列表")
	@RequestMapping(value="/getRoleList",method=RequestMethod.GET)
	public JSONObject getRoleList() {
		List<Role> roleList = roleServices.getAllRoleList();
		JSONObject obj = new JSONObject();
		obj.put("code", 200);
		obj.put("roleList", roleList);
		return obj;
	}
	
	
	

}
