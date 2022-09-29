package xpit.top.action.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import xpit.top.action.domain.ResponseResult;
import xpit.top.action.entity.Admin;
import xpit.top.action.service.LoginService;

import javax.annotation.Resource;

/**
 * @author Pu Tongjiao
 * @date 2022/9/29 13:54
 */
@Api(tags = "用户登录")
@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    @ApiOperation(value = "登录")
    @PostMapping("login")
    public ResponseResult login(@RequestBody Admin admin) {
        return loginService.login(admin);
    }

    @ApiOperation(value = "注册")
    @PostMapping("register")
    public ResponseResult register(@RequestBody Admin admin) {
        return loginService.register(admin);

    }

    @ApiOperation(value = "退出登录")
    @GetMapping("logout")
    public ResponseResult logout() {
        return loginService.logout();
    }
}
