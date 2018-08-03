package com.wiesel.system.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wiesel.system.entity.Dept;
import com.wiesel.system.mapper.DeptMapper;
import com.wiesel.system.service.IDeptService;

/**
 * <p>
 * 部门管理 服务实现类
 * </p>
 *
 * @author wuj123
 * @since 2018-08-03
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

}
