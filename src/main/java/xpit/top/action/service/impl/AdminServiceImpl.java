package xpit.top.action.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xpit.top.action.domain.ResponseResult;
import xpit.top.action.domain.vo.UserInfoVo;
import xpit.top.action.entity.Admin;
import xpit.top.action.mapper.AdminMapper;
import xpit.top.action.service.AdminService;
import xpit.top.action.utils.BeanCopyUtils;
import xpit.top.action.utils.SecurityUtils;

/**
 * (Admin)表服务实现类
 *
 * @author PuTongjiao
 * @since 2022-09-29 14:14:28
 */
@Service("adminService")
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private SecurityUtils securityUtils;


    @Override
    public ResponseResult userInfo() {
        Admin admin = securityUtils.getLoginUser().getAdmin();
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(admin, UserInfoVo.class);
        return ResponseResult.okResult(userInfoVo);
    }
}

