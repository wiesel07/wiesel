package com.wiesel.system.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wiesel.common.base.entity.Tree;
import com.wiesel.common.utils.BuildTree;
import com.wiesel.system.entity.Menu;
import com.wiesel.system.mapper.MenuMapper;
import com.wiesel.system.service.IMenuService;

/**
 * <p>
 * 菜单管理 服务实现类
 * </p>
 *
 * @author wuj
 * @since 2018-07-04
 */
@Service
@Transactional(readOnly = true,rollbackFor = Exception.class)
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

	
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
		List<Tree<Menu>> list = BuildTree.buildList(trees, "0");
		return list;
	}

}
