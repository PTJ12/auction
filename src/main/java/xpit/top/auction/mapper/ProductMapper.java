package xpit.top.auction.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import xpit.top.auction.entity.Product;


/**
 * (Product)表数据库访问层
 *
 * @author PuTongjiao
 * @since 2022-10-02 22:09:56
 */
@Repository
public interface ProductMapper extends BaseMapper<Product> {

}

