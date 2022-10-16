package xpit.top.auction.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xpit.top.auction.domain.ResponseResult;
import xpit.top.auction.entity.Admin;

import java.io.IOException;


/**
 * (Admin)表服务接口
 *
 * @author PuTongjiao
 * @since 2022-09-29 14:14:28
 */
public interface AdminService extends IService<Admin> {

    ResponseResult userInfo();

    ResponseResult transactionToUser(Double value, String addres) throws Exception;

    ResponseResult getBalance() throws IOException;
}

