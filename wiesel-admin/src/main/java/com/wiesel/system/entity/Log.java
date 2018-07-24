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
 * <p>
 * 系统日志
 * </p>
 *
 * @author wuj
 * @since 2018-07-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_log")
public class Log extends Model<Log> {

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
        return this.id;
    }

}
