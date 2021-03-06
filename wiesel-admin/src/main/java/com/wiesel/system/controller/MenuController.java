package com.wiesel.system.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wiesel.common.constant.UrlConstant;
import com.wiesel.common.enums.ErrorCode;
import com.wiesel.system.controller.req.MenuReq;
import com.wiesel.system.entity.Menu;
import com.wiesel.system.entity.RoleMenu;
import com.wiesel.system.service.IMenuService;
import com.wiesel.system.service.IRoleMenuService;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;
import wiesel.common.api.ApiResult;
import wiesel.common.base.entity.ZtreeNode;
import wiesel.common.enums.ApiErrorCode;
import wiesel.common.exception.ApiException;

/**
 *
 * @ClassName 类名：MenuController
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
@Controller
@RequestMapping(UrlConstant.root + "/sys/menu")
public class MenuController {
	String prefix = UrlConstant.prefix + "system/menu";
	@Autowired
	private IMenuService menuService;

	@Autowired
	private IRoleMenuService roleMenuService;
	
	@RequiresPermissions("sys:menu:menu")
	@GetMapping()
	String menu(Model model) {
		return prefix + "/menu";
	}

	@RequiresPermissions("sys:menu:menu")
	@RequestMapping("/list")
	@ResponseBody
	List<Menu> list() {
		EntityWrapper<Menu> wrapper = new EntityWrapper<>();
		List<Menu> menus = menuService.selectList(wrapper);
		return menus;
	}

	@ApiOperation(value = "新增菜单")
	@RequiresPermissions("sys:menu:add")
	@GetMapping("/add/{pId}")
	String add(Model model, @PathVariable("pId") String pId) {
		
		Long menuId = Long.valueOf(pId);
		model.addAttribute("pId", menuId);
		if (menuId == 0) {
			model.addAttribute("pName", "根目录");
		} else {
			model.addAttribute("pName", menuService.selectById(menuId).getName());
		}
		return prefix + "/add";
	}

	@ApiOperation(value = "编辑菜单")
	@RequiresPermissions("sys:menu:edit")
	@GetMapping("/edit/{pId}")
	String edit(Model model, @PathVariable("pId") String pId) {
		Long menuId = Long.valueOf(pId);
		Menu menu = menuService.selectById(menuId);
		Long parentId = menu.getParentId();
		if (parentId == 0) {
			model.addAttribute("pName", "根目录");
		} else {
			model.addAttribute("pName", menuService.selectById(parentId).getName());
		}
		model.addAttribute("menu", menu);
		return prefix + "/edit";
	}

	@ApiOperation(value = "保存菜单")
	@RequiresPermissions("sys:menu:add")
	@PostMapping("/save")
	@ResponseBody
	public ApiResult<String> save(MenuReq menuReq) {
		Menu menu = new Menu();
		BeanUtil.copyProperties(menuReq, menu);

		if (!menuService.insert(menu)) {
			throw new ApiException(ApiErrorCode.DB_INSERT_FAIL);
		}
		return ApiResult.ok();
	}

	@ApiOperation(value = "更新菜单")
	@RequiresPermissions("sys:menu:edit")
	@PostMapping("/update")
	@ResponseBody
	public ApiResult<String> update(MenuReq menuReq) {
		Menu menu = new Menu();
		BeanUtil.copyProperties(menuReq, menu);

		if (!menuService.updateById(menu)) {
			throw new ApiException(ApiErrorCode.DB_UPDATE_FAIL);
		}
		return ApiResult.ok();
	}

	@ApiOperation(value = "删除菜单")
	@RequiresPermissions("sys:menu:delete")
	@PostMapping("/delete")
	@ResponseBody
	public ApiResult<String> delete(String id) {
		if (StringUtils.isEmpty(id)) {
			return ApiResult.error(ErrorCode.PARAM_IS_NULL);
		}
		Long menuId = Long.valueOf(id);
		EntityWrapper<RoleMenu> roleMenuWrapper = new EntityWrapper<RoleMenu>();
		roleMenuWrapper.eq(RoleMenu.MENU_ID, menuId);
		
		if (roleMenuService.selectCount(roleMenuWrapper)>0) {
			throw new ApiException(ErrorCode.MENU_HAS_USERD);
		}
		
		menuService.deleteById(menuId);
		
		return ApiResult.ok();
	}

	@GetMapping("/tree")
	@ResponseBody
	List<ZtreeNode> tree() {
		List<ZtreeNode> trees = menuService.getTree();
		return trees;
	}

	@GetMapping("/tree/{roleId}")
	@ResponseBody
	List<ZtreeNode> tree(@PathVariable("roleId") Long roleId) {
		List<ZtreeNode> trees = menuService.getTree(roleId);
		return trees;
	}

	@ApiIgnore
	@GetMapping("/treeView/{id}")
	String treeView(@PathVariable("id") String id, Model model) {
		model.addAttribute("treeId", id);
		return prefix + "/menuTree";
	}

	@ApiOperation(value = "校验权限标识符是否存在")
	@PostMapping("/checkPerms")
	@ResponseBody
	boolean checkPerms(@RequestParam String perms) {

		EntityWrapper<Menu> wrapper = new EntityWrapper<>();
		wrapper.eq(Menu.PERMS, perms);

		return !(menuService.selectCount(wrapper) > 0);
	}

}
