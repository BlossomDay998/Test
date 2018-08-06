package com.sam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sam.dao.RoleCustomMapper;
import com.sam.dao.RoleMapper;
import com.sam.entity.Role;

@Service
public class RoleServices {
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private RoleCustomMapper roleCustomMapper;

	public Role getRoleById(Integer id) {
		return roleMapper.selectByPrimaryKey(id);
	}
	

	public Role getRoleByRoleName(String roleName) {
		return roleCustomMapper.selectByRoleName(roleName);
	}

	public int insert(String roleName) {
		Role role = new Role();
		role.setRoleName(roleName);
		return roleMapper.insert(role);
	}

	public int updateByPrimaryKey(Integer id, String roleName) {
		Role role = new Role();
		role.setId(id);
		role.setRoleName(roleName);
		return roleMapper.updateByPrimaryKey(role);
	}

	public int delete(Integer id) {

		return roleMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 
	 * 
	 * @return
	 * @作者：刘鑫 
	 * @描述：查询所有角色
	 */
	public List<Role> getAllRoleList() {
		return roleCustomMapper.selectRoleList();
	}

}
