package com.wiesel.soccer.entity;

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
 * @ClassName 类名：Soccer
 * @Description 功能说明： 
 *              <p>
 *              TODO
 *              </p>
 ***********************************************************************
 * @date 创建日期：2018-12-05
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ****************************修订记录************************************
 * 
 *          2018-12-05 wuj 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("soccer")
public class Soccer extends Model<Soccer> {

	private static final long serialVersionUID = 1L;

	
    /**
	 * 赛事编号
	 */
	private String code;
	
    /**
	 * 赛事
	 */
	private String type;
	
    /**
	 * 主队
	 */
	private String homeTeam;
	
    /**
	 * 客队
	 */
	private String guestTeam;
	
    /**
	 * 初盘推荐
	 */
	private String prediction;
	
    /**
	 * 推荐人
	 */
	private String userName;
	
    /**
	 * 赛果
	 */
	private Integer result;
	
    /**
	 * 总分
	 */
		private String gmtCreate;
	
    /**
	 * 最后更新时间
	 */
		private String gmtModified;
	
    /**
	 * 主队分数
	 */
	private Integer homeScore;
	
    /**
	 * 客队分数
	 */
	private Integer guestScore;
	
    /**
	 * 分差
	 */
	private Integer diffScore;
	
    /**
	 * 总分
	 */
	private Integer totalScore;
	
	
	/**
	 * 是否有水位，1有水位，0无水位
	 */
	private Integer waterLevel;
	
    /**
	 * ID
	 */
    @TableId(value = "id", type = IdType.ID_WORKER)
	private Long id;

    public static final String CODE = "code";
    public static final String TYPE = "type";
    public static final String HOME_TEAM = "home_team";
    public static final String GUEST_TEAM = "guest_team";
    public static final String PREDICTION = "prediction";
    public static final String USER_NAME = "user_name";
    public static final String RESULT = "result";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    public static final String HOME_SCORE = "home_score";
    public static final String GUEST_SCORE = "guest_score";
    public static final String DIFF_SCORE = "diff_score";
    public static final String TOTAL_SCORE = "total_score";
    public static final String WATER_LEVEL = "water_level";
    public static final String ID = "id";

    @Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return this.id;
	}
}
