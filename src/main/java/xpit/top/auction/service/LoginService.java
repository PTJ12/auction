package xpit.top.auction.service;

import xpit.top.auction.domain.ResponseResult;
import xpit.top.auction.entity.Admin;

/**
 * @author Pu Tongjiao
 * @date 2022/9/29 13:53
 */
public interface LoginService {
    ResponseResult login(Admin admin);


    ResponseResult register(Admin admin);

    ResponseResult logout();
}
