package com.wiesel.common.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.wiesel.system.entity.User;

import lombok.experimental.UtilityClass;

/** 
*
* @ClassName   类名：PasswordHelper 
* @Description 功能说明：用户注册密码加密工具类
* <p>
* TODO
*</p>
************************************************************************
* @date        创建日期：2018年7月20日
* @author      创建人：wuj
* @version     版本号：V1.0
*<p>
***************************修订记录*************************************
* 
*   2018年7月20日   wuj   创建该类功能。
*
***********************************************************************
*</p>
*/
@UtilityClass
public class PasswordHelper {
	//private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
		private String algorithmName = "md5";
		private int hashIterations = 1;

		public void encryptPassword(User user) {
			//String salt=randomNumberGenerator.nextBytes().toHex();

			String newPassword = new SimpleHash(algorithmName, user.getPassword(),  ByteSource.Util.bytes(user.getUserId()), hashIterations).toHex();
			//String newPassword = new SimpleHash(algorithmName, user.getPassword()).toHex();
			user.setPassword(newPassword);

		}
		
//		public static void main(String[] args) {
//			User user = new User();
//			user.setUsername("admin");
//			user.setPassword("123456");
//			
//			String newPassword = new SimpleHash("MD5", user.getPassword(),  ByteSource.Util.bytes(user.getUsername()), 1).toHex();
//			//String newPassword = new SimpleHash(algorithmName, user.getPassword()).toHex();
//			user.setPassword(newPassword);
//		System.out.println(user.getPassword());
//		
//		}
}
