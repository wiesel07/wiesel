package com.wiesel.system.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.omg.CORBA.LongLongSeqHelper;
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
import com.wiesel.common.base.entity.ZtreeNode;
import com.wiesel.common.exception.CommonException;
import com.wiesel.common.utils.IDUtils;
import com.wiesel.common.utils.R;
import com.wiesel.system.controller.req.DeptReq;
import com.wiesel.system.entity.Dept;
import com.wiesel.system.entity.User;
import com.wiesel.system.service.IDeptService;
import com.wiesel.system.service.IUserService;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 *
 * @ClassName 类名：Dept2Controller
 * @Description 功能说明：
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2018年8月23日
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录*************************************
 * 
 *          2018年8月23日 wuj 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */
@Controller
@RequestMapping("/sys/dept")
public class Dept2Controller {

	private String prefix = "system/dept";

	@Autowired
	private IDeptService deptService;

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
	public List<Dept> list() {
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
	String edit(@PathVariable("pId") String pId, Model model) {
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
	 */
	@ApiOperation(value = "保存部门")
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("sys:dept:add")
	public R save(DeptReq deptReq) {
		Dept dept = new Dept();
		BeanUtil.copyProperties(deptReq, dept);
		dept.setDeptId(IDUtils.newID());
		if (!deptService.insert(dept)) {
			throw new CommonException("新增部门出错");
		}
		return R.ok();
	}

	/**
	 * 修改
	 */
	@ApiOperation(value = "修改部门")
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sys:dept:edit")
	public R update(DeptReq deptReq) {
		Dept dept = new Dept();
		BeanUtil.copyProperties(deptReq, dept);
		if (!deptService.updateById(dept)) {
			throw new CommonException("部门修改过程出错");
		}
		return R.ok();
	}

	@ApiOperation(value = "删除部门")
	@PostMapping("/delete")
	@ResponseBody
	@RequiresPermissions("sys:dept:delete")
	public R delete(String id) {
		if (StringUtils.isEmpty(id)) {
			return R.error("参数不能为空");
		}
		deptService.deleteById(Long.valueOf(id));
		return R.ok();
	}

	@ApiOperation(value = "批量删除部门")
	@PostMapping("/batchDelete")
	@ResponseBody
	@RequiresPermissions("sys:dept:batchDelete")
	public R batchDelete(String[] ids) {
		if (ids.length <= 0) {
			return R.error("参数不能为空");
		}
		List<Long> delIds = new ArrayList<>();
		for (String id : ids) {
			delIds.add(Long.valueOf(id));
		}

		deptService.deleteBatchIds(delIds);
		
		return R.ok();
	}
}
