package com.wiesel.system.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wiesel.system.entity.Menu;
import com.wiesel.system.entity.RoleMenu;
import com.wiesel.system.mapper.MenuMapper;
import com.wiesel.system.mapper.RoleMenuMapper;
import com.wiesel.system.service.IMenuService;

import wiesel.common.base.entity.Tree;
import wiesel.common.base.entity.ZtreeNode;
import wiesel.common.utils.BuildTreeUtils;

/**
*
* @ClassName 类名：MenuServiceImpl
* @Description 功能说明：
*              <p>
*              TODO
*              </p>
************************************************************************
* @date 创建日期：2018年7月4日
* @author 创建人：wuj
* @version 版本号：V1.0
*          <p>
***************************          修订记录*************************************
* 
*          2018年7月4日 wuj 创建该类功能。
*
***********************************************************************
*          </p>
*/
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

	@Autowired
	private RoleMenuMapper roleMenuMapper;

	@Override
	public Set<String> listPerms(Long userId) {
		List<String> perms = this.baseMapper.listUserPerms(userId);
		Set<String> permsSet = new HashSet<>();
		for (String perm : perms) {
			if (StringUtils.isNotBlank(perm)) {
				permsSet.addAll(Arrays.asList(perm.trim().split(",")));
			}
		}
		return permsSet;
	}

	@Override
	public List<Tree<Menu>> listMenuTree(Long id) {
		List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
		List<Menu> menus = this.baseMapper.listMenuByUserId(id);
		for (Menu sysMenu : menus) {
			Tree<Menu> tree = new Tree<Menu>();
			tree.setId(sysMenu.getMenuId().toString());
			tree.setParentId(sysMenu.getParentId().toString());
			tree.setText(sysMenu.getName());
			Map<String, Object> attributes = new HashMap<>(16);
			attributes.put("url", sysMenu.getUrl());
			attributes.put("icon", sysMenu.getIcon());
			tree.setAttributes(attributes);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		List<Tree<Menu>> list = BuildTreeUtils.buildList(trees, "0");
		return list;
	}

	@Override
	public List<ZtreeNode> getTree() {
		List<ZtreeNode> trees = new ArrayList<ZtreeNode>();

		EntityWrapper<Menu> wrapper = new EntityWrapper<>();
		wrapper.orderBy(Menu.TYPE, true);
		wrapper.orderBy(Menu.PARENT_ID, true);
		wrapper.orderBy(Menu.MENU_ID, true);
		List<Menu> menus = this.baseMapper.selectList(wrapper);

		for (Menu menu : menus) {
			ZtreeNode tree = new ZtreeNode();
			tree.setId(menu.getMenuId().toString());
			tree.setPid(menu.getParentId().toString());
			tree.setName(menu.getName());
			tree.setChecked(false);
			trees.add(tree);
		}
		return trees;
	}

	@Override
	public List<ZtreeNode> getTree(Long id) {
		List<ZtreeNode> trees = new ArrayList<ZtreeNode>();

		EntityWrapper<Menu> menuWrapper = new EntityWrapper<>();
		menuWrapper.orderBy(Menu.TYPE, true);
		menuWrapper.orderBy(Menu.PARENT_ID, true);
		menuWrapper.orderBy(Menu.MENU_ID, true);
		// 获取所有菜单信息
		List<Menu> menus = this.baseMapper.selectList(menuWrapper);

		// 获取角色对应菜单信息
		EntityWrapper<RoleMenu> roleMenuWrapper = new EntityWrapper<>();
		roleMenuWrapper.eq(RoleMenu.ROLE_ID, id);
		List<RoleMenu> roleMenus = roleMenuMapper.selectList(roleMenuWrapper);

		for (Menu menu : menus) {
			ZtreeNode tree = new ZtreeNode();
			tree.setId(menu.getMenuId().toString());
			tree.setPid(menu.getParentId().toString());
			tree.setName(menu.getName());
			tree.setChecked(false);
			for (RoleMenu roleMenu : roleMenus) {
				if (menu.getMenuId().longValue() == roleMenu.getMenuId().longValue()) {
					tree.setChecked(true);
					break;
				}
			}
			trees.add(tree);
		}
		return trees;
	}
	

}
