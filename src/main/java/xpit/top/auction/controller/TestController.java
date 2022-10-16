package xpit.top.auction.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xpit.top.auction.annotation.SystemLog;
import xpit.top.auction.domain.ResponseResult;

/**
 * @author Pu Tongjiao
 * @date 2022/9/29 17:02
 */
@Api(tags = "测试接口")
@RestController
public class TestController {

    @GetMapping("test")
    @ApiOperation(value = "测试")
    @SystemLog(businessName = "测试接口")
    public ResponseResult test() {
        return ResponseResult.okResult();

    }
}
