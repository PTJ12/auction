package xpit.top.action;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Transfer;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;
import org.web3j.utils.Convert;
import xpit.top.action.contract.EcommerceStore;
import xpit.top.action.domain.ResponseResult;
import xpit.top.action.domain.SystemVariable;
import xpit.top.action.domain.vo.ProductVo;
import xpit.top.action.entity.Bid;
import xpit.top.action.service.ContractService;
import xpit.top.action.utils.CopySourceName;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@SpringBootTest(classes = ActionApplication.class)
class ActionApplicationTests {

    @Autowired
    private Web3j web3j;

    private String credtial = "ae8a8b42e726d856dd0a237d00b533c9c734d9ea";
    private String myAddress = "0x68755De2D9BD6e21C3481818C24900E41aacFd23";
    @Test
    void deployContract() throws Exception {

//
//        EthAccounts send = web3j.ethAccounts().send();
//        List<String> accounts = send.getAccounts();
//        System.out.println(accounts);
//        Credentials credentials = Credentials.create(credtial);
//        ContractGasProvider provider = new StaticGasProvider(BigInteger.valueOf(20000000000L), BigInteger.valueOf(6721975L));
//        Dapp_sol_Dapp contract = Dapp_sol_Dapp.deploy(
//                web3j, credentials, provider
//        ).send();
//        String contractAddress = contract.getContractAddress();
//        System.out.println(contractAddress);
    }

    @Test
    void loadContract() throws Exception {
//        Credentials credentials = Credentials.create(credtial);
//        ContractGasProvider provider = new StaticGasProvider(BigInteger.valueOf(20000000000L), BigInteger.valueOf(6721975L));
//        EthAccounts ethAccounts = web3j.ethAccounts().send();
//        List<String> accounts = ethAccounts.getAccounts();
//        String s = accounts.get(0);
//        System.out.println(s);
//        Dapp_sol_Dapp dapp_sol_dapp = Dapp_sol_Dapp.load("0xF4ff5b5310A1AadE7620F55f33bAEb699D4c04aF", web3j, credentials, provider);
//        TransactionReceipt send = dapp_sol_dapp.store(200).send();
//        BigInteger send1 = dapp_sol_dapp.retrieve().send();
//        System.out.println(send1);

    }

    @Test
    void transfer() throws Exception {


//        System.out.println(new File("src/main/resources").getCanonicalPath()+"/contracts/UTC--2022-09-26T11-56-56.753599233Z--cf2e74246f6a91706c7c6c0da6d6478d3f76227d");
        Credentials credentials = WalletUtils.loadCredentials("qwer1234", SystemVariable.PRIVATE_KEY_PATH);
//        System.out.println(credentials);
        //发送交易
        TransactionReceipt transactionReceipt = Transfer.sendFunds(web3j, credentials, myAddress, BigDecimal.valueOf(10.0), Convert.Unit.ETHER).send();
        String transactionHash = transactionReceipt.getTransactionHash();
        System.out.println(transactionHash);
    }

    @Resource
    private ContractService contractService;

    @Test
    void deployEcommerce() throws Exception {
        contractService.createAccount();
    }

    @Test
    void name() {
        Class c = ProductVo.class;
        for (Field f : c.getDeclaredFields()) {
            if (f.isAnnotationPresent(CopySourceName.class)){
                CopySourceName annotation = f.getAnnotation(CopySourceName.class);
                System.out.println(f.getName() + "--->" + annotation.value());
            }
        }
    }

    @Test
    void test21() throws ParseException {
        Long l = 1664254549962L;
        BigInteger i = BigInteger.valueOf(l);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s =simpleDateFormat.format(new Date(Long.parseLong(String.valueOf(i))));
        System.out.println(s);

        String as = "2022-09-27 16:19:30";

        Date date = new Date();
        date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(as);
        long time = date.getTime();
        String s1 =simpleDateFormat.format(new Date(Long.parseLong(String.valueOf(time))));
        System.out.println(time);
        System.out.println(s1);

    }

    @Test
    void test34() {
        Bid bid = new Bid(BigInteger.valueOf(1), "8", "hello", BigInteger.valueOf(12));
        String contractAddress = "0x8d063243193D429A3988316CB83dC4b04edFF775";
        Credentials credentials = Credentials.create("193f9d950707033705d0b9fdbe7b79a5bc97c9c9827523a3a6cdaa5defd92bf8");
        ContractGasProvider provider = new StaticGasProvider(BigInteger.valueOf(20000000000L), BigInteger.valueOf(6721975L));
        EcommerceStore load = EcommerceStore.load(contractAddress, web3j, credentials, provider);
//        BigInteger weiValue = BigInteger.valueOf(Long.parseLong(String.valueOf(Convert.toWei(String.valueOf(bid.getWeiValue()), Convert.Unit.ETHER))));
//        System.out.println(weiValue);
        try {
            TransactionReceipt transactionReceipt = load.bid(bid.getProductId(), bid.getAmount(), bid.getSecret(), bid.getWeiValue()).send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
