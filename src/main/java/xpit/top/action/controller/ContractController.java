package xpit.top.action.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.web3j.crypto.CipherException;
import xpit.top.action.domain.ResponseResult;
import xpit.top.action.domain.vo.ProductVo;
import xpit.top.action.entity.Bid;
import xpit.top.action.entity.Product;
import xpit.top.action.service.ContractService;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigInteger;

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
    public ResponseResult getProduct(@PathVariable long productId) throws Exception {
        return contractService.contractLoadGetProduct(productId);
    }

    @ApiOperation(value = "添加产品")
    @PostMapping("addProduct")
    public void addProduct(@RequestBody Product product) throws Exception {
        contractService.contractLoadAddProduct(product);
    }

    @ApiOperation(value = "竞拍")
    @PostMapping("bid")
    public ResponseResult bid(@RequestBody Bid bid) throws Exception {
        return contractService.bid(bid);
    }


    //揭示报价
    @ApiOperation(value = "揭示报价")
    @GetMapping("revealBid")
    public ResponseResult revealBid(Bid bid) throws Exception {
        return contractService.revealBid(bid);
    }

    //获取最高竞价信息
    @ApiOperation(value = "获取最高竞价信息")
    @GetMapping("highestBidderInfo/{productId}")
    public ResponseResult highestBidderInfo(@PathVariable long productId) throws Exception {
        return contractService.highestBidderInfo(productId);

    }

    //获取竞价次数
    @ApiOperation(value = "获取竞价次数")
    @GetMapping("totalBids/{productId}")
    public ResponseResult totalBids(@PathVariable long productId) throws Exception {
        return contractService.totalBids(productId);
    }
    //结束竞拍
    @ApiOperation(value = "结束竞拍")
    @GetMapping("finalizeAuction/{productId}")
    public ResponseResult finalizeAuction(@PathVariable long productId) throws Exception {
        return contractService.finalizeAuction(productId);
    }

}
