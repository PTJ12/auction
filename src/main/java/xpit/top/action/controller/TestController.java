package xpit.top.action.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xpit.top.action.domain.ResponseResult;

/**
 * @author Pu Tongjiao
 * @date 2022/9/29 17:02
 */
@RestController
public class TestController {

    @GetMapping("test")
    public ResponseResult test() {
        return ResponseResult.okResult();

    }
}
