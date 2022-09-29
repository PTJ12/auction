package xpit.top.action.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

/**
 * @author Pu Tongjiao
 * @date 2022/9/27 16:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private BigInteger id; //id

    private String name; //名称

    private String category; //分类

    private String imageLink; //图片

    private String descLink; //描述

    private String auctionStartTime; // 竞拍开始时间

    private String auctionEndTime; // 竞拍结束时间

    private BigInteger startPrice; // 价格

    private BigInteger status; // 存储的状态

    private BigInteger condition; //商品状态

}
