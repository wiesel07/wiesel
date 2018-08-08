package com.wiesel.system.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.wiesel.common.base.entity.Tree;
import com.wiesel.common.base.entity.ZtreeNode;
import com.wiesel.common.utils.R;
import com.wiesel.system.entity.Dept;
import com.wiesel.system.service.IDeptService;

import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 部门管理 前端控制器
 * </p>
 *
 * @author wuj123
 * @since 2018-08-03
 */
@Controller
@RequestMapping("/sys/dept")
public class DeptController {

	private String prefix = "system/dept";
	@Autowired
	private IDeptService deptService;

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

	@GetMapping("/edit/{pId}")
	@RequiresPermissions("sys:dept:edit")
	String edit(@PathVariable("pId") Long pId, Model model) {
		Dept dept = deptService.selectById(pId);
		model.addAttribute("deptId", pId);
		model.addAttribute("dept", dept);
		if ("888888".equals(Long.valueOf(dept.getParentId()))) {
			model.addAttribute("parentDeptName", "无");
		} else {
			Dept parDept =  deptService.selectById(dept.getParentId());
			model.addAttribute("parentDeptName", parDept.getName());
		}
		return prefix + "/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("sys:dept:add")
	public R save(Dept sysDept) {
		// if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
		// return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		// }
		// if (sysDeptService.save(sysDept) > 0) {
		// return R.ok();
		// }
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sys:dept:edit")
	public R update(Dept sysDept) {
		// if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
		// return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		// }
		// if (sysDeptService.update(sysDept) > 0) {
		// return R.ok();
		// }
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/delete")
	@ResponseBody
	@RequiresPermissions("sys:dept:delete")
	public R remove(Long deptId) {
		// if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
		// return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		// }
		// Map<String, Object> map = new HashMap<String, Object>();
		// map.put("parentId", deptId);
		// if(sysDeptService.count(map)>0) {
		// return R.error(1, "包含下级部门,不允许修改");
		// }
		// if(sysDeptService.checkDeptHasUser(deptId)) {
		// if (sysDeptService.remove(deptId) > 0) {
		// return R.ok();
		// }
		// }else {
		// return R.error(1, "部门包含用户,不允许修改");
		// }
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchDelete")
	@ResponseBody
	@RequiresPermissions("sys:delete:batchDelete")
	public R remove(@RequestParam("ids[]") Long[] deptIds) {
		// if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
		// return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		// }
		// sysDeptService.batchRemove(deptIds);
		return R.ok();
	}

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

	@GetMapping("/treeView")
	String treeView() {
		return prefix + "/deptTree";
	}

}
