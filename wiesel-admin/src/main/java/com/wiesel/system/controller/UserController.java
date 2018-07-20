package com.wiesel.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wiesel.common.controller.BaseController;
import com.wiesel.system.entity.User;
import com.wiesel.system.service.IUserService;

import io.swagger.annotations.Api;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wuj
 * @since 2018-07-04
 */
@Api("用户表相应接口")
@Controller
@RequestMapping("/sys/user")
public class UserController extends BaseController{

	private String prefix = "system/user";

	@Autowired
	private IUserService userService;

	@GetMapping("/personal")
	String personal(Model model) {
		User user = userService.selectById(getUserId());
		model.addAttribute("user", user);
		//爱好性别取 字典表 待补充
//		model.addAttribute("hobbyList", dictService.getHobbyList(userDO));
//		model.addAttribute("sexList", dictService.getSexList());
		return prefix + "/personal";
	}
}
