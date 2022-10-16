package xpit.top.auction.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import xpit.top.auction.utils.CopySourceName;

import java.math.BigInteger;

/**
 * @author Pu Tongjiao
 * @date 2022/9/27 13:13
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
public class ProductVo {
    @CopySourceName("value1")
    private BigInteger id; //id

    @CopySourceName("value2")
    private String name; //名称

    @CopySourceName("value3")
    private String category; //分类

    @CopySourceName("value4")
    private String imageLink; //图片

    @CopySourceName("value5")
    private String descLink; //描述

    private String auctionStartTime; // 竞拍开始时间

    private String auctionEndTime; // 竞拍结束时间

    @CopySourceName("value8")
    private BigInteger startPrice; // 价格

    @CopySourceName("value9")
    private BigInteger status; // 存储的状态

    @CopySourceName("value10")
    private BigInteger condition; //商品状态


}
