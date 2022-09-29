package xpit.top.action.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xpit.top.action.domain.ResponseResult;
import xpit.top.action.entity.Admin;


/**
 * (Admin)表服务接口
 *
 * @author PuTongjiao
 * @since 2022-09-29 14:14:28
 */
public interface AdminService extends IService<Admin> {

    ResponseResult userInfo();
}

