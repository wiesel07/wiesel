package com.wiesel.system.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wiesel.common.utils.MD5Utils;
import com.wiesel.common.utils.R;



/** 
*
* @ClassName   类名：LoginController 
* @Description 功能说明：
* <p>
* TODO
*</p>
************************************************************************
* @date        创建日期：2018年7月4日
* @author      创建人：wuj
* @version     版本号：V1.0
*<p>
***************************修订记录*************************************
* 
*   2018年7月4日   wuj   创建该类功能。
*
***********************************************************************
*</p>
*/
@Controller
public class LoginController {
	@GetMapping({ "/", "" })
	String welcome(Model model) {
		//return "redirect:/blog";
		return "index_v1";
	}
	@GetMapping("/login")
	String login() {
		return "login";
	}
	
	
	@GetMapping({ "/index" })
	String index(Model model) {
		System.out.println("a");
//		List<Tree<MenuDO>> menus = menuService.listMenuTree(getUserId());
//		model.addAttribute("menus", menus);
//		model.addAttribute("name", getUser().getName());
//		FileDO fileDO = fileService.get(getUser().getPicId());
//		if(fileDO!=null&&fileDO.getUrl()!=null){
//			if(fileService.isExist(fileDO.getUrl())){
//				model.addAttribute("picUrl",fileDO.getUrl());
//			}else {
//				model.addAttribute("picUrl","/img/photo_s.jpg");
//			}
//		}else {
//			model.addAttribute("picUrl","/img/photo_s.jpg");
//		}
//		model.addAttribute("username", getUser().getUsername());
		return "index_v1";
	}
	
	@ResponseBody
	@PostMapping(value="/login",produces="application/json;charset=UTF-8")
	R ajaxLogin(String username, String password) {

		password = MD5Utils.encrypt(username, password);
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			return R.ok();
		} catch (AuthenticationException e) {
			return R.error("用户或密码错误");
		}
	}

}
