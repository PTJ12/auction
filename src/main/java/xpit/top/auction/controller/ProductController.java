package xpit.top.auction.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xpit.top.auction.domain.ResponseResult;
import xpit.top.auction.service.ProductService;


/**
 * @Author PuTongjiao
 * @Date 2022/10/3 15:44
 */
@Api(tags = "商品")
@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation("获取所有商品")
    @GetMapping("getProduct")
    private ResponseResult getProduct() {
        return ResponseResult.okResult(productService.list());
    }

}
