package com.wiesel.system.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * @ClassName 类名：Dict
 * @Description 功能说明： 字典表
 *              <p>
 *              TODO
 *              </p>
 ***********************************************************************
 * @date 创建日期：2018-09-01
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ****************************          修订记录************************************
 * 
 *          2018-09-01 wuj 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_dict")
public class Dict extends Model<Dict> {

	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@TableId(value = "dict_id", type = IdType.ID_WORKER)
	private Long dictId;

	/**
	 * 名称
	 */
	private String dictName;

	/**
	 * 数据值
	 */
	private String dictValue;

	/**
	 * 类型
	 */
	private String dictType;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 排序（升序）
	 */
	private BigDecimal sort;

	/**
	 * 父级编号
	 */
	private Long parentId;

	/**
	 * 创建者
	 */
	private Integer createBy;

	/**
	 * 备注信息
	 */
	private String remarks;

	/**
	 * 删除标记
	 */
	private String delFlag;

	/**
	 * 创建时间
	 */
	private String gmtCreate;

	/**
	 * 最后更新时间
	 */
	private String gmtModified;

	public static final String DICT_ID = "dict_id";

	public static final String DICT_NAME = "dict_name";

	public static final String DICT_VALUE = "dict_value";

	public static final String DICT_TYPE = "dict_type";

	public static final String DESCRIPTION = "description";

	public static final String SORT = "sort";

	public static final String PARENT_ID = "parent_id";

	public static final String CREATE_BY = "create_by";

	public static final String REMARKS = "remarks";

	public static final String DEL_FLAG = "del_flag";

	public static final String GMT_CREATE = "gmt_create";

	public static final String GMT_MODIFIED = "gmt_modified";

	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return this.dictId;
	}
}
