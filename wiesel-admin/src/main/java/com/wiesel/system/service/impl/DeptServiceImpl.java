package com.wiesel.system.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wiesel.system.entity.Dept;
import com.wiesel.system.mapper.DeptMapper;
import com.wiesel.system.service.IDeptService;

/**
*
* @ClassName 类名：DeptServiceImpl
* @Description 功能说明：
*              <p>
*              TODO
*              </p>
************************************************************************
* @date 创建日期：2018年7月4日
* @author 创建人：wuj
* @version 版本号：V1.0
*          <p>
***************************          修订记录*************************************
* 
*          2018年7月4日 wuj 创建该类功能。
*
***********************************************************************
*          </p>
*/
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

}
