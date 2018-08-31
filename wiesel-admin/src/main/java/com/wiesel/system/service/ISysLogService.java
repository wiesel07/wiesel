package com.wiesel.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.wiesel.system.entity.SysLog;

/**
 *
 * @ClassName 类名：ISysLogService
 * @Description 功能说明： 系统日志
 *              <p>
 *              TODO
 *              </p>
 ***********************************************************************
 * @date 创建日期：2018-08-25 15:51:26
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ****************************修订记录************************************
 * 
 *          2018-08-25 15:51:26 wuj 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */
 
public interface ISysLogService extends IService<SysLog> {
    /**
     * 
     * <p>函数名称：        </p>
     * <p>功能说明：新增日志
     *
     * </p>
     *<p>参数说明：</p>
     * @param sysLog
     *
     * @date   创建时间：2018年8月25日
     * @author 作者：wuj
     */
	void addSysLog(SysLog sysLog);
}