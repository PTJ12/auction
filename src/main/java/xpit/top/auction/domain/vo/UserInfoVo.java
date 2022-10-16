package xpit.top.auction.domain.vo;

import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (Admin)表实体类
 *
 * @author PuTongjiao
 * @since 2022-09-29 14:28:18
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVo {
    private Integer id;

    private String username;

    private String avatar;

    private String address;

    private String privateKey;

    private String publicKey;

    private Date createTime;

    private Date updateTime;



}


