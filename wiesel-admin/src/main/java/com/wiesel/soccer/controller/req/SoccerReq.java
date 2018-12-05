package com.wiesel.soccer.controller.req;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * @ClassName 类名：SoccerReq
 * @Description 功能说明： 
 *              <p>
 *              TODO
 *              </p>
 ***********************************************************************
 * @date 创建日期：2018-12-05
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ****************************修订记录*************************************
 * 
 *          2018-12-05 wuj 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */
@Data
@Accessors(chain = true)
public class SoccerReq  {

	
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
	
    /**
	 * 最后更新时间
	 */
	
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
	 * ID
	 */
	private Long id;

}
