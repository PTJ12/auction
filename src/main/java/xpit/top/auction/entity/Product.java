package xpit.top.auction.entity;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Product)表实体类
 *
 * @author PuTongjiao
 * @since 2022-10-03 12:30:20
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("product")
public class Product  {
    //id@TableId
    private Integer id;

    //名称
    private String name;
    //分类
    private String category;
    //图片链接
    private String imageLink;
    //描述
    private String descLink;
    //开始时间
    private String auctionStartTime;
    //结束时间
    private String auctionEndTime;
    //初始价格
    private Double startPrice;
    //存储的状态
    private String status;
    //商品状态
    private String conditions;



}

