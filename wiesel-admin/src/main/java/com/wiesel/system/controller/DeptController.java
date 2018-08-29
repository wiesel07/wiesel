package com.wiesel.system.controller;

import java.util.ArrayList;
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
import com.wiesel.system.controller.req.DeptReq;
import com.wiesel.system.entity.Dept;
import com.wiesel.system.entity.User;
import com.wiesel.system.service.IDeptService;
import com.wiesel.system.service.IUserService;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;
import wiesel.common.api.ApiResult;
import wiesel.common.base.entity.ZtreeNode;
import wiesel.common.enums.ApiErrorCode;
import wiesel.common.exception.ApiException;
import wiesel.common.utils.IDUtils;

/**
 *
 * @ClassName 类名：MenuController
 * @Description 功能说明：
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2018年8月3日
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录*************************************
 * 
 *          2018年8月3日 wuj 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */
@Controller
@RequestMapping(UrlConstant.root + "sys/dept")
public class DeptController {

	private String prefix = UrlConstant.prefix + "system/dept";
	@Autowired
	private IDeptService deptService;

	@Autowired
	private IUserService userService;

	@ApiIgnore
	@GetMapping()
	@RequiresPermissions("sys:dept:dept")
	String dept() {
		return prefix + "/dept";
	}

	@ApiOperation(value = "获取部门列表", notes = "")
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("sys:dept:dept")
	List<Dept> list() {
		EntityWrapper<Dept> wrapper = new EntityWrapper<>();
		return deptService.selectList(wrapper);
	}

	@ApiOperation(value = "新增部门")
	@RequiresPermissions("sys:dept:add")
	@GetMapping("/add/{pId}")
	String add(@PathVariable("pId") String pId, Model model) {
		Long deptId = Long.valueOf(pId);
		model.addAttribute("pId", deptId);
		if (deptId == 0) {
			model.addAttribute("pName", "总部门");
		} else {

			model.addAttribute("pName", deptService.selectById(deptId).getName());
		}
		return prefix + "/add";
	}

	@ApiOperation(value = "编辑部门")
	@GetMapping("/edit/{pId}")
	@RequiresPermissions("sys:dept:edit")
	public String edit(@PathVariable("pId") String pId, Model model) {
		Long deptId = Long.valueOf(pId);
		Dept dept = deptService.selectById(deptId);
		model.addAttribute("dept", dept);

		if (0 == dept.getParentId()) {
			model.addAttribute("parentDeptName", "无");
		} else {
			Dept parDept = deptService.selectById(dept.getParentId());
			model.addAttribute("parentDeptName", parDept.getName());
		}
		return prefix + "/edit";
	}

	/**
	 * 保存
	 * 
	 * @param <T>
	 */
	@ApiOperation(value = "保存部门")
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("sys:dept:add")
	public ApiResult<String> save(DeptReq deptReq) {
		Dept dept = new Dept();
		BeanUtil.copyProperties(deptReq, dept);
		dept.setDeptId(IDUtils.newID());
		if (!deptService.insert(dept)) {
			throw new ApiException(ApiErrorCode.DB_INSERT_FAIL);
		}
		return ApiResult.ok();
	}

	/**
	 * 修改
	 */
	@ApiOperation(value = "修改部门")
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sys:dept:edit")
	public ApiResult<String> update(DeptReq deptReq) {
		Dept dept = new Dept();
		BeanUtil.copyProperties(deptReq, dept);
		if (!deptService.updateById(dept)) {
			throw new ApiException(ApiErrorCode.DB_UPDATE_FAIL);
		}
		return ApiResult.ok();
	}

	@ApiOperation(value = "删除部门")
	@PostMapping("/delete")
	@ResponseBody
	@RequiresPermissions("sys:dept:delete")
	public ApiResult<String> delete(String id) {
		if (StringUtils.isEmpty(id)) {
			return ApiResult.error(ErrorCode.PARAM_IS_NULL);
		}

		Long pId = Long.valueOf(id);

		EntityWrapper<Dept> deptWrapper = new EntityWrapper<>();
		deptWrapper.eq(Dept.PARENT_ID, pId);
		if (deptService.selectCount(deptWrapper) > 0) {
			throw new ApiException("包含下级部门，不允许删除");
		}
		EntityWrapper<User> userWrapper = new EntityWrapper<>();
		userWrapper.eq(User.DEPT_ID, pId);
		if (userService.selectCount(userWrapper) > 0) {
			throw new ApiException("部门包含用户,不允许删除");
		}

		if (!deptService.deleteById(pId)) {
			throw new ApiException(ApiErrorCode.DB_DELETE_FAIL);
		}
		return ApiResult.ok();
	}

	@ApiOperation(value = "批量删除部门")
	@PostMapping("/batchDelete")
	@ResponseBody
	@RequiresPermissions("sys:delete:batchDelete")
	public ApiResult<String> batchDelete(@RequestParam("ids[]") String[] deptIds) {

		return ApiResult.ok("");
	}

	@ApiOperation(value = "获取部门树结构")
	@GetMapping("/tree")
	@ResponseBody
	public List<ZtreeNode> tree() {
		List<ZtreeNode> trees = new ArrayList<ZtreeNode>();

		EntityWrapper<Dept> wrapper = new EntityWrapper<>();
		List<Dept> depts = deptService.selectList(wrapper);
		for (Dept dept : depts) {
			ZtreeNode tree = new ZtreeNode();
			tree.setId(dept.getDeptId().toString());
			tree.setPid(dept.getParentId().toString());
			tree.setName(dept.getName());
			tree.setChecked(true);
			trees.add(tree);
		}

		return trees;
	}

	@ApiIgnore
	@GetMapping("/treeView/{id}")
	String treeView(@PathVariable("id") String id, Model model) {
		model.addAttribute("treeId", id);
		return prefix + "/deptTree";
	}

}
