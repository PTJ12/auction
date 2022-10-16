package xpit.top.auction.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import xpit.top.auction.annotation.SystemLog;
import xpit.top.auction.domain.ResponseResult;
import xpit.top.auction.entity.Bid;
import xpit.top.auction.entity.Product;
import xpit.top.auction.service.ContractService;

import javax.annotation.Resource;

/**
 * @author Pu Tongjiao
 * @date 2022/9/27 13:10
 */
@Api(tags = "合约产品管理")
@RestController
@RequestMapping("contract")
public class ContractController {

    @Resource
    private ContractService contractService;

    @ApiOperation(value = "根据id获取产品")
    @GetMapping("getProduct/{productId}")
    @SystemLog(businessName = "根据id获取产品")
    public ResponseResult getProduct(@PathVariable long productId) throws Exception {
        return contractService.contractLoadGetProduct(productId);
    }

    @ApiOperation(value = "添加产品")
    @PostMapping("addProduct")
    @SystemLog(businessName = "添加产品")
    public ResponseResult addProduct(@RequestBody Product product) throws Exception {
//        return ResponseResult.okResult(product);
        return contractService.contractLoadAddProduct(product);
    }

    @ApiOperation(value = "竞拍")
    @PostMapping("bid")
    @SystemLog(businessName = "竞拍")
    public ResponseResult bid(@RequestBody Bid bid) throws Exception {
        return contractService.bid(bid);
    }


    //揭示报价
    @ApiOperation(value = "揭示报价")
    @GetMapping("revealBid")
    @SystemLog(businessName = "揭示报价")
    public ResponseResult revealBid(Bid bid) throws Exception {
        return contractService.revealBid(bid);
    }

    //获取最高竞价信息
    @ApiOperation(value = "获取最高竞价信息")
    @GetMapping("highestBidderInfo/{productId}")
    @SystemLog(businessName = "获取最高竞价信息")
    public ResponseResult highestBidderInfo(@PathVariable long productId) throws Exception {
        return contractService.highestBidderInfo(productId);

    }

    //获取竞价次数
    @ApiOperation(value = "获取竞价次数")
    @GetMapping("totalBids/{productId}")
    @SystemLog(businessName = "获取竞价次数")
    public ResponseResult totalBids(@PathVariable long productId) throws Exception {
        return contractService.totalBids(productId);
    }
    //结束竞拍
    @ApiOperation(value = "结束竞拍")
    @GetMapping("finalizeAuction/{productId}")
    @SystemLog(businessName = "结束竞拍")
    public ResponseResult finalizeAuction(@PathVariable long productId) throws Exception {
        return contractService.finalizeAuction(productId);
    }

}
