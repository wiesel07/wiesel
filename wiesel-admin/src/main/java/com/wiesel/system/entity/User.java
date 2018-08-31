package com.wiesel.system.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
*
* @ClassName 类名：Dept
* @Description 功能说明：部门管理
*              <p>
*              TODO
*              </p>
************************************************************************
* @date 创建日期：2018年8月3日
* @author 创建人：wuj
* @version 版本号：V1.0
*          <p>
***************************          修订记录*************************************
* 
*          2018年8月3日 wuj 创建该类功能。
*
***********************************************************************
*          </p>
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.ID_WORKER)
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    private String name;
    /**
     * 密码
     */
    private String password;
    private Long deptId;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 状态 0:禁用，1:正常
     */
    private Integer status;
    /**
     * 创建用户id
     */
    private Long userIdCreate;
    /**
     * 创建时间
     */
    private String gmtCreate;
    /**
     * 修改时间
     */
    private String gmtModified;
    /**
     * 性别
     */
    
    private Integer sex;
    /**
     * 出身日期
     */
    private String birth;
    private Long picId;
    /**
     * 现居住地
     */
    private String liveAddress;
    /**
     * 爱好
     */
    private String hobby;
    /**
     * 省份
     */
    private String province;
    /**
     * 所在城市
     */
    private String city;
    /**
     * 所在地区
     */
    private String district;


    public static final String USER_ID = "user_id";

    public static final String USERNAME = "username";

    public static final String NAME = "name";

    public static final String PASSWORD = "password";

    public static final String DEPT_ID = "dept_id";

    public static final String EMAIL = "email";

    public static final String MOBILE = "mobile";

    public static final String STATUS = "status";

    public static final String USER_ID_CREATE = "user_id_create";

    public static final String GMT_CREATE = "gmt_create";

    public static final String GMT_MODIFIED = "gmt_modified";

    public static final String SEX = "sex";

    public static final String BIRTH = "birth";

    public static final String PIC_ID = "pic_id";

    public static final String LIVE_ADDRESS = "live_address";

    public static final String HOBBY = "hobby";

    public static final String PROVINCE = "province";

    public static final String CITY = "city";

    public static final String DISTRICT = "district";

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
