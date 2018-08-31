package com.wiesel.system.service.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wiesel.system.entity.SysLog;
import com.wiesel.system.mapper.SysLogMapper;
import com.wiesel.system.service.ISysLogService;

/**
 *
 * @ClassName 类名：SysLogServiceImpl
 * @Description 功能说明： 系统日志
 *              <p>
 *              TODO
 *              </p>
 ***********************************************************************
 * @date 创建日期：2018-08-25 15:51:26
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ****************************          修订记录************************************
 * 
 *          2018-08-25 15:51:26 wuj 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */

@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {

	@Async
	@Override
	public void addSysLog(SysLog sysLog) {
		this.baseMapper.insert(sysLog);
	}

}
