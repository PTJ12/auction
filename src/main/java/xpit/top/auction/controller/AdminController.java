package xpit.top.auction.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xpit.top.auction.annotation.SystemLog;
import xpit.top.auction.domain.ResponseResult;
import xpit.top.auction.service.AdminService;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author Pu Tongjiao
 * @date 2022/9/29 18:49
 */
@Api(tags = "用户信息")
@RestController
@RequestMapping("admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    @ApiOperation("获取用户信息")
    @GetMapping("userInfo")
    @SystemLog(businessName = "获取用户信息")
    public ResponseResult userInfo() {
        return adminService.userInfo();

    }

    @ApiOperation("转币")
    @PostMapping("transactionToUser")
    @SystemLog(businessName = "转币")
    public ResponseResult transactionToUser(Double value, String address) throws Exception {
        return adminService.transactionToUser(value, address);
    }

    @ApiOperation(value = "获取资产")
    @GetMapping("getBalance")
    @SystemLog(businessName = "获取资产")
    public ResponseResult getBalance() throws IOException {
        return adminService.getBalance();
    }
}
