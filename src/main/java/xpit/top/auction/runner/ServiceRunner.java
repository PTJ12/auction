package xpit.top.auction.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import xpit.top.auction.constants.SystemConstants;
import xpit.top.auction.contract.EcommerceStore;
import xpit.top.auction.entity.Product;
import xpit.top.auction.mapper.ProductMapper;
import xpit.top.auction.utils.DateUtils;

import java.io.IOException;
import java.util.Objects;

@Component
@Slf4j
public class ServiceRunner implements ApplicationRunner {

    @Autowired
    private Web3j web3j;

    private ContractGasProvider gasProvider = new DefaultGasProvider();

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void run(ApplicationArguments args) {
        new Thread(() -> {
            try {
                addProduct();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void addProduct() throws CipherException, IOException {

//        Credentials credentials = WalletUtils.loadCredentials("qwer1234", SystemVariable.PRIVATE_KEY_PATH);
        Credentials credentials = Credentials.create(SystemConstants.PRIVATE_KEY);
        EcommerceStore load = EcommerceStore.load(SystemConstants.CONTRACT_ADDRESS, web3j, credentials, gasProvider);
        EthFilter filter = new EthFilter(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST, SystemConstants.CONTRACT_ADDRESS);
        load.newProductEventFlowable(filter).subscribe(event -> {
            log.info("event事件触发");
            Product product1 = productMapper.selectById(event._productId.intValue());
            if (Objects.isNull(product1)){
                Product product = new Product();
                product.setId(event._productId.intValue());
                product.setName(event._name);
                product.setCategory(event._category);
                product.setImageLink(event._imageLink);
                product.setDescLink(event._descLink);
                product.setAuctionStartTime(DateUtils.timestampToDate(event._auctionStartTime));
                product.setAuctionEndTime(DateUtils.timestampToDate(event._auctionEndTime));
                product.setStatus("0");
                product.setConditions(String.valueOf(event._productCondition));
                product.setStartPrice(event._startPrice.doubleValue());
                int insert = productMapper.insert(product);
                log.info("添加到数据库---" + insert);
            }else {
                log.info("该条数据已经添加过");
            }

        });
    }
}
