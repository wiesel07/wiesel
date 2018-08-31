package com.wiesel.common.aspect;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.wiesel.common.annotation.Log;
import com.wiesel.common.utils.ShiroUtils;
import com.wiesel.system.entity.SysLog;
import com.wiesel.system.service.ISysLogService;

import wiesel.common.utils.IPUtils;

/**
 *
 * @ClassName 类名：LogAspect
 * @Description 功能说明：
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2018年8月25日
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录*************************************
 * 
 *          2018年8月25日 wuj 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */
@Aspect
@Component
public class LogAspect {
	
	@Autowired
	ISysLogService sysLogService;

	@Pointcut("@annotation(com.wiesel.common.annotation.Log)")
	public void logPointCut() {
	}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		// 执行方法
		Object result = point.proceed();
		// 执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;
		// 异步保存日志
		saveLog(point, time);
		return result;
	}

	private void saveLog(ProceedingJoinPoint joinPoint, long time) throws InterruptedException {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		SysLog sysLog = new SysLog();
		
		Log log = method.getAnnotation(Log.class);
		if (log != null) {
			// 注解上的描述
			sysLog.setOperation(log.value());
		}
		// 请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		sysLog.setMethod(className + "." + methodName + "()");
		// 请求的参数
		Object[] args = joinPoint.getArgs();
		try {
			if (null!=args[0]) {
				String params=JSON.toJSONString(args[0]);
				sysLog.setParams(params);
			}
		} catch (Exception e) {

		}
		// 获取request
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		// 设置IP地址
		sysLog.setIp(IPUtils.getIpAddr(request));
		// 用户名
		if (null==ShiroUtils.getUser() ) {
				sysLog.setUserId(-1L);
				sysLog.setUsername("获取用户信息为空");
		} else {
			sysLog.setUserId(ShiroUtils.getUserId());
			sysLog.setUsername(ShiroUtils.getUser().getUsername());
		}
		sysLog.setTime((int) time);
	
		// 保存系统日志
		sysLogService.addSysLog(sysLog);
	}
}
