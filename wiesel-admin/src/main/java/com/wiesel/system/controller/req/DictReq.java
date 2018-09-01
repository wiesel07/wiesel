package com.wiesel.system.controller.req;

import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * @ClassName 类名：DictReq
 * @Description 功能说明： 字典表
 *              <p>
 *              TODO
 *              </p>
 ***********************************************************************
 * @date 创建日期：2018-09-01
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ****************************修订记录*************************************
 * 
 *          2018-09-01 wuj 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */
@Data
@Accessors(chain = true)
public class DictReq  {

	
    /**
	 * 编号
	 */
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
	
    /**
	 * 最后更新时间
	 */

}
