package com.wiesel.system.service.impl;

import com.wiesel.system.entity.User;
import com.wiesel.system.mapper.UserMapper;
import com.wiesel.system.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wuj
 * @since 2018-07-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
