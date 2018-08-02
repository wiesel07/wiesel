package com.wiesel.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wiesel.common.base.entity.ZtreeNode;
import com.wiesel.system.service.IMenuService;

/**
 * <p>
 * 菜单管理 前端控制器
 * </p>
 *
 * @author wuj
 * @since 2018-07-04
 */
@Controller
@RequestMapping("/sys/menu")
public class MenuController {

	@Autowired
	private IMenuService menuService;

	@GetMapping("/tree")
	@ResponseBody
	List<ZtreeNode> tree() {
		List<ZtreeNode>  trees = menuService.getTree();
		return trees;
	}
	
	@GetMapping("/tree/{roleId}")
	@ResponseBody
	List<ZtreeNode> tree(@PathVariable("roleId") Long roleId) {
		List<ZtreeNode>  trees = menuService.getTree(roleId);
		return trees;
	}
	
//	@GetMapping("/tree/{roleId}")
//	@ResponseBody
//	R tree(@PathVariable("roleId") Long roleId) {
//		Tree<Menu> tree = menuService.getTree(roleId);
//		
//		
//		return null;
//	}
}
