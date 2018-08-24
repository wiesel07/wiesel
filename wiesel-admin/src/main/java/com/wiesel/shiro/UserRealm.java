package com.wiesel.shiro;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;

import com.wiesel.common.config.ApplicationContextRegister;
import com.wiesel.common.utils.ShiroUtils;
import com.wiesel.system.entity.User;
import com.wiesel.system.mapper.UserMapper;
import com.wiesel.system.service.IMenuService;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @ClassName 类名：UserRealm
 * @Description 功能说明：身份校验核心类
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
@Slf4j
public class UserRealm extends AuthorizingRealm {

	/**
	 * 认证.登录
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		log.info("认证:" + new Date());
		UsernamePasswordToken utoken = (UsernamePasswordToken) token;// 获取用户输入的token
		String username = utoken.getUsername();

		Map<String, Object> map = new HashMap<>(16);
		map.put("username", username);
		String password = new String((char[]) utoken.getPassword());

		UserMapper userMapper = ApplicationContextRegister.getBean(UserMapper.class);
		User user = new User();
		user.setUsername(username);
		user = userMapper.selectOne(user);

		// 账号不存在
		if (user == null||user.getUserId()==null) {
			throw new UnknownAccountException("账号或密码不正确");
		}

		// 密码错误
		if (!password.equals(user.getPassword())) {
			throw new IncorrectCredentialsException("账号或密码不正确");
		}

		// 账号锁定
		if (user.getStatus() == 0) {
			throw new LockedAccountException("账号已被锁定,请联系管理员");
		}
		// 明文: 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(), getName());

		
//		 SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
//	                user, //用户
//	                user.getPassword(), //密码
//	                ByteSource.Util.bytes(username),
//	                getName()  //realm name
//	        );
		
		// 当验证都通过后，把用户信息放在session里
		Session session = SecurityUtils.getSubject().getSession();
		session.setAttribute("userSession", user);
		session.setAttribute("userSessionId", user.getUserId());
		return info;
	}

	/**
	 * 此方法调用hasRole,hasPermission的时候才会进行回调.
	 * <p>
	 * 权限信息.(授权): 1、如果用户正常退出，缓存自动清空； 2、如果用户非正常退出，缓存自动清空；
	 * 3、如果我们修改了用户的权限，而用户不退出系统，修改的权限无法立即生效。 （需要手动编程进行实现；放在service进行调用）
	 * 在权限修改后调用realm中的方法，realm已经由spring管理，所以从spring中获取realm实例，调用clearCached方法；
	 * :Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等。
	 *
	 * @param principals
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		log.info("授权:" + new Date());
		/*
		 * 当没有使用缓存的时候，不断刷新页面的话，这个代码会不断执行， 当其实没有必要每次都重新设置权限信息，所以我们需要放到缓存中进行管理；
		 * 当放到缓存中时，这样的话，doGetAuthorizationInfo就只会执行一次了， 缓存过期之后会再次执行。
		 */
		Long userId = ShiroUtils.getUserId();
		IMenuService iMenuService = ApplicationContextRegister.getBean(IMenuService.class);
		Set<String> perms = iMenuService.listPerms(userId);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(perms);
		return info;
	}

	/**
	 * 根据userId 清除当前session存在的用户的权限缓存
	 * 
	 * @param userIds
	 *            已经修改了权限的userId
	 */
	// public void clearUserAuthByUserId(List<Integer> userIds) {
	// if (null == userIds || userIds.size() == 0)
	// return;
	// // 获取所有session
	// Collection<Session> sessions = redisSessionDAO.getActiveSessions();
	// // 定义返回
	// List<SimplePrincipalCollection> list = new
	// ArrayList<SimplePrincipalCollection>();
	// for (Session session : sessions) {
	// // 获取session登录信息。
	// Object obj =
	// session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
	// if (null != obj && obj instanceof SimplePrincipalCollection) {
	// // 强转
	// SimplePrincipalCollection spc = (SimplePrincipalCollection) obj;
	// // 判断用户，匹配用户ID。
	// obj = spc.getPrimaryPrincipal();
	// if (null != obj && obj instanceof User) {
	// User user = (User) obj;
	// System.out.println("user:" + user);
	// // 比较用户ID，符合即加入集合
	// if (null != user && userIds.contains(user.getId())) {
	// list.add(spc);
	// }
	// }
	// }
	// }
	// RealmSecurityManager securityManager = (RealmSecurityManager)
	// SecurityUtils.getSecurityManager();
	// MyShiroRealm realm = (MyShiroRealm)
	// securityManager.getRealms().iterator().next();
	// for (SimplePrincipalCollection simplePrincipalCollection : list) {
	// realm.clearCachedAuthorizationInfo(simplePrincipalCollection);
	// }
	// }
}
