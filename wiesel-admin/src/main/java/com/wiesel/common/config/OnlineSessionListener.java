package com.wiesel.common.config;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

/** 
*
* @ClassName   类名：OnlineSessionListener 
* @Description 功能说明：
* <p>
* TODO
*</p>
************************************************************************
* @date        创建日期：2018年8月24日
* @author      创建人：wuj
* @version     版本号：V1.0
*<p>
***************************修订记录*************************************
* 
*   2018年8月24日   wuj   创建该类功能。
*
***********************************************************************
*</p>
*/
public class OnlineSessionListener implements SessionListener {

	private final AtomicInteger sessionCount = new AtomicInteger(0);

	@Override
	public void onStart(Session session) {
		sessionCount.incrementAndGet();
	}

	@Override
	public void onStop(Session session) {
		sessionCount.decrementAndGet();
	}

	@Override
	public void onExpiration(Session session) {
		sessionCount.decrementAndGet();

	}

	public int getSessionCount() {
		return sessionCount.get();
	}

}
