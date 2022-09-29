package xpit.top.action.service;

import xpit.top.action.domain.ResponseResult;
import xpit.top.action.entity.Admin;

/**
 * @author Pu Tongjiao
 * @date 2022/9/29 13:53
 */
public interface LoginService {
    ResponseResult login(Admin admin);


    ResponseResult register(Admin admin);

    ResponseResult logout();
}
