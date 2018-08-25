package com.wiesel.system.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * @ClassName 类名：Log
 * @Description 功能说明： 系统日志
 *              <p>
 *              TODO
 *              </p>
 ***********************************************************************
 * @date 创建日期：2018-08-25 15:41:03
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ****************************修订记录************************************
 * 
 *          2018-08-25 15:41:03 wuj 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_log")
public class SysLog extends Model<SysLog> {

	private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
	private Long id;
	
    /**
	 * 用户id
	 */
	private Long userId;
	
    /**
	 * 用户名
	 */
	private String username;
	
    /**
	 * 用户操作
	 */
	private String operation;
	
    /**
	 * 响应时间
	 */
	private Integer time;
	
    /**
	 * 请求方法
	 */
	private String method;
	
    /**
	 * 请求参数
	 */
	private String params;
	
    /**
	 * IP地址
	 */
	private String ip;
	
    /**
	 * 创建时间
	 */
	private Date gmtCreate;

    public static final String ID = "id";
    public static final String USER_ID = "user_id";
    public static final String USERNAME = "username";
    public static final String OPERATION = "operation";
    public static final String TIME = "time";
    public static final String METHOD = "method";
    public static final String PARAMS = "params";
    public static final String IP = "ip";
    public static final String GMT_CREATE = "gmt_create";

    @Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return this.id;
	}
}
