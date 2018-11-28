package com.wiesel.system.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.wiesel.system.entity.Dict;
import com.wiesel.system.mapper.DictMapper;
import com.wiesel.system.service.IDictService;

/**
 *
 * @ClassName 类名：DictServiceImpl
 * @Description 功能说明： 字典表
 *              <p>
 *              TODO
 *              </p>
 ***********************************************************************
 * @date 创建日期：2018-09-01
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ****************************修订记录************************************
 * 
 *          2018-09-01 wuj 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {

}

