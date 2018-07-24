package com.wiesel.system.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wiesel.system.entity.Role;
import com.wiesel.system.mapper.RoleMapper;
import com.wiesel.system.service.IRoleService;


/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author wuj
 * @since 2018-07-04
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
	
}
