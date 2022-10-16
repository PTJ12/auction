package xpit.top.auction.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xpit.top.auction.entity.Product;
import xpit.top.auction.mapper.ProductMapper;
import xpit.top.auction.service.ProductService;

/**
 * (Product)表服务实现类
 *
 * @author PuTongjiao
 * @since 2022-10-02 22:09:56
 */
@Service("productService")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}

