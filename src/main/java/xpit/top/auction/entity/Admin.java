package xpit.top.auction.entity;

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
@TableName("sys_admin")
public class Admin  {
    @TableId(type = IdType.AUTO)
    private Integer id;
    
    private String username;

    private String avatar;

    private String password;

    private String address;
    
    private String privateKey;
    
    private String publicKey;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;



}

