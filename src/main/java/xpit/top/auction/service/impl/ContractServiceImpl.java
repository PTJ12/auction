package xpit.top.auction.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.*;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple10;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;
import xpit.top.auction.constants.SystemConstants;
import xpit.top.auction.contract.EcommerceStore;
import xpit.top.auction.domain.ResponseResult;
import xpit.top.auction.domain.vo.HighestBidderInfoVo;
import xpit.top.auction.domain.vo.ProductVo;
import xpit.top.auction.entity.Bid;
import xpit.top.auction.entity.Product;
import xpit.top.auction.service.ContractService;
import xpit.top.auction.utils.DateUtils;
import xpit.top.auction.utils.SecurityUtils;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * @author Pu Tongjiao
 * @date 2022/9/26 15:54
 */
@Service(value = "contractService")
public class ContractServiceImpl implements ContractService {

    @Autowired
    private Web3j web3j;

    private ContractGasProvider gasProvider = new DefaultGasProvider();

    @Autowired
    private SecurityUtils securityUtils;

    public EcommerceStore ecommerceStore(){
        String privateKey = securityUtils.getPrivateKey();
        Credentials credentials = Credentials.create(privateKey);
        return EcommerceStore.load(SystemConstants.CONTRACT_ADDRESS, web3j, credentials, gasProvider);
    }

    @Override
    public void createAccount() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, CipherException, IOException {
        ECKeyPair ecKeyPair = Keys.createEcKeyPair();//调用Keys的静态方法创建密钥对
        String privateKey = ecKeyPair.getPrivateKey().toString(16);//获取私钥
        String publicKey = ecKeyPair.getPublicKey().toString(16);//获取公钥
        String address = Keys.getAddress(publicKey);//获取地址值
        System.out.println("Your private key:"+privateKey);
        System.out.println("Your public key:"+publicKey);
        System.out.println("Your address:"+address);
        String password = "123456";
        File file = new File("D:\\");
        String s = WalletUtils.generateWalletFile(password, ecKeyPair, file, true);
        System.out.println(s);
    }

    /**
     * 使用java部署合约在调用bid时候会发生异常*
     * @return
     * @throws CipherException
     * @throws IOException
     */
    @Override
    public ResponseResult contractDeploy() throws CipherException, IOException {
//        Credentials credentials = WalletUtils.loadCredentials("qwer1234", SystemVariable.PRIVATE_KEY_PATH);
//        ContractGasProvider provider = new StaticGasProvider(BigInteger.valueOf(20000000000L), BigInteger.valueOf(6721975L));
//
//        String contractAddress = null;
//        try {
//            EcommerceStore contract = EcommerceStore.deploy(
//                    web3j, credentials, provider
//            ).send();
//            contractAddress = contract.getContractAddress();
//            System.out.println(contractAddress);
//        }  catch (Exception e) {
//            e.printStackTrace();
//        }
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult contractLoadAddProduct(Product product) throws Exception {
        TransactionReceipt transactionReceipt = ecommerceStore().addProductToStore(product.getName(),product.getCategory(), product.getImageLink(), product.getDescLink(), DateUtils.dateToTimestamp(product.getAuctionStartTime()), DateUtils.dateToTimestamp(product.getAuctionEndTime()), BigInteger.valueOf(product.getStartPrice().longValue()), BigInteger.valueOf(Long.valueOf(product.getConditions()))).send();
        return ResponseResult.okResult(transactionReceipt.isStatusOK());
    }

    @Override
    public ResponseResult contractLoadGetProduct(long productId) throws Exception {
        Tuple10<BigInteger, String, String, String, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger> tuple10 = ecommerceStore().getProduct(BigInteger.valueOf(productId)).send();

        ProductVo productVo = new ProductVo(tuple10.component1(),
                tuple10.component2(),
                tuple10.component3(),
                tuple10.component4(),
                tuple10.component5(),
                DateUtils.timestampToDate(tuple10.component6()),
                DateUtils.timestampToDate(tuple10.component7()),
                tuple10.component8(),
                tuple10.component9(),
                tuple10.component10());

        return ResponseResult.okResult(productVo);
    }

    @Override
    public ResponseResult bid(Bid bid) throws Exception {

        BigInteger weiValue = BigInteger.valueOf(Long.parseLong(String.valueOf(Convert.toWei(String.valueOf(bid.getWeiValue()), Convert.Unit.ETHER))));
        System.out.println(weiValue);
        TransactionReceipt transactionReceipt = ecommerceStore().bid(bid.getProductId(), bid.getAmount(), bid.getSecret(), weiValue).send();
        return ResponseResult.okResult(transactionReceipt.isStatusOK());
    }

    @Override
    public ResponseResult revealBid(Bid bid) throws Exception {
        TransactionReceipt transactionReceipt = ecommerceStore().revealBid(bid.getProductId(), bid.getAmount(), bid.getSecret()).send();
        return ResponseResult.okResult(transactionReceipt.isStatusOK());
    }

    @Override
    public ResponseResult highestBidderInfo(long productId) throws Exception {
        Tuple3<String, BigInteger, BigInteger> tuple3 = ecommerceStore().highestBidderInfo(BigInteger.valueOf(productId)).send();
        HighestBidderInfoVo highestBidderInfoVo = new HighestBidderInfoVo(tuple3.component1(), tuple3.component2(), tuple3.component3());
        return ResponseResult.okResult(highestBidderInfoVo);
    }

    @Override
    public ResponseResult totalBids(long productId) throws Exception {
        BigInteger total = ecommerceStore().totalBids(BigInteger.valueOf(productId)).send();
        return ResponseResult.okResult(total);
    }

    @Override
    public ResponseResult finalizeAuction(long productId) throws Exception {
        TransactionReceipt transactionReceipt = ecommerceStore().finalizeAuction(BigInteger.valueOf(productId)).send();
        return ResponseResult.okResult(transactionReceipt.isStatusOK());
    }

}
