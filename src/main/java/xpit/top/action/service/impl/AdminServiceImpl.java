package xpit.top.action.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xpit.top.action.entity.Admin;
import xpit.top.action.mapper.AdminMapper;
import xpit.top.action.service.AdminService;

/**
 * (Admin)表服务实现类
 *
 * @author PuTongjiao
 * @since 2022-09-29 14:14:28
 */
@Service("adminService")
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

}

