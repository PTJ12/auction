package xpit.top.action.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xpit.top.action.entity.Admin;
import xpit.top.action.entity.LoginUser;
import xpit.top.action.service.AdminService;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author PuTongjiao
 * @date 2022/8/21 0:13
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        Admin admin = adminService.getOne(new LambdaQueryWrapper<Admin>()
                .eq(Admin::getUsername, username));
        //判断是否查询到用户 如果没有查到 抛出异常
        if (Objects.isNull(admin)){
            throw new RuntimeException("用户不存在");
        }

        //TODO 查询权限信息 封装

        //返回用户信息
        return new LoginUser(admin);
    }
}
