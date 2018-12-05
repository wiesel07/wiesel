package com.wiesel.soccer.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.wiesel.soccer.entity.Soccer;
import com.wiesel.soccer.mapper.SoccerMapper;
import com.wiesel.soccer.service.ISoccerService;

/**
 *
 * @ClassName 类名：SoccerServiceImpl
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
@Service
public class SoccerServiceImpl extends ServiceImpl<SoccerMapper, Soccer> implements ISoccerService {

}

