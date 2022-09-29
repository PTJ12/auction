package xpit.top.action.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xpit.top.action.domain.ResponseResult;
import xpit.top.action.service.AdminService;

/**
 * @author Pu Tongjiao
 * @date 2022/9/29 18:49
 */
@Api(tags = "用户信息")
@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @ApiOperation("获取用户信息")
    @GetMapping("userInfo")
    public ResponseResult userInfo() {
        return adminService.userInfo();

    }
}
