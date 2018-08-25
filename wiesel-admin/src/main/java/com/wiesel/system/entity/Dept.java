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
@TableName("sys_dept")
public class Dept extends Model<Dept> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "dept_id", type = IdType.ID_WORKER)
    private Long deptId;
    /**
     * 上级部门ID，一级部门为0
     */
    private Long parentId;
    /**
     * 部门名称
     */
    private String name;
    /**
     * 排序
     */
    private Integer orderNum;
    /**
     * 部门状态（0正常 1停用）
     */
    private Integer delFlag;


    public static final String DEPT_ID = "dept_id";

    public static final String PARENT_ID = "parent_id";

    public static final String NAME = "name";

    public static final String ORDER_NUM = "order_num";

    public static final String DEL_FLAG = "del_flag";

    @Override
    protected Serializable pkVal() {
        return this.deptId;
    }

}
