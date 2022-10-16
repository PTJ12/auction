package xpit.top.action;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.protocol.Web3j;
import xpit.top.auction.AuctionApplication;
import xpit.top.auction.utils.DateUtils;

import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;


@SpringBootTest(classes = AuctionApplication.class)
class AuctionApplicationTests {

//    @Autowired
//    private ProductService productService;
//
//    @Test
//    void name() {
////        Product product = new Product(1, "honor 6", "Cell Phones & Accessories", "imagelink", "desclink", "1970-01-20 14:13:41", "1970-01-20 14:13:41", new Double(2), 0, 0);
//        Product product = new Product();
//        product.setId(8);
//        product.setName("honor");
//        product.setCategory("Cell Phones & Accessories");
//        product.setImageLink("imageLink");
//        product.setDescLink("descLink");
//        product.setAuctionStartTime("2022-01-20 14:13:41");
//        product.setAuctionEndTime("2022-01-20 14:13:41");
//        product.setStatus("0");
//        product.setConditions("0");
//        product.setStartPrice(2.1);
//        boolean save = productService.save(product);
//
////        int insert = productMapper.insert(product);
//        System.out.println(save);
//    }



    @Autowired
    private Web3j web3j;

    @Test
    void name() throws IOException {
//        EthTransaction send = web3j.ethGetTransactionByHash("0x62E04b39B03c731697c71c9b9A09Ab06f1A0D5c2").send();
//        Optional<Transaction> transaction = send.getTransaction();
//        System.out.println();
    }

    //    private String credtial = "ae8a8b42e726d856dd0a237d00b533c9c734d9ea";
//    private String myAddress = "0x62E04b39B03c731697c71c9b9A09Ab06f1A0D5c2";
//    @Test
//    void deployContract() throws Exception {
//
////
////        EthAccounts send = web3j.ethAccounts().send();
////        List<String> accounts = send.getAccounts();
////        System.out.println(accounts);
////        Credentials credentials = Credentials.create(credtial);
////        ContractGasProvider provider = new StaticGasProvider(BigInteger.valueOf(20000000000L), BigInteger.valueOf(6721975L));
////        Dapp_sol_Dapp contract = Dapp_sol_Dapp.deploy(
////                web3j, credentials, provider
////        ).send();
////        String contractAddress = contract.getContractAddress();
////        System.out.println(contractAddress);
//    }
//
//    @Test
//    void loadContract() throws Exception {
////        Credentials credentials = Credentials.create(credtial);
////        ContractGasProvider provider = new StaticGasProvider(BigInteger.valueOf(20000000000L), BigInteger.valueOf(6721975L));
////        EthAccounts ethAccounts = web3j.ethAccounts().send();
////        List<String> accounts = ethAccounts.getAccounts();
////        String s = accounts.get(0);
////        System.out.println(s);
////        Dapp_sol_Dapp dapp_sol_dapp = Dapp_sol_Dapp.load("0xF4ff5b5310A1AadE7620F55f33bAEb699D4c04aF", web3j, credentials, provider);
////        TransactionReceipt send = dapp_sol_dapp.store(200).send();
////        BigInteger send1 = dapp_sol_dapp.retrieve().send();
////        System.out.println(send1);
//
//    }
//
//    @Test
//    void transfer() throws Exception {
//
//        String path = new File("src/main/resources").getCanonicalPath() + "/contracts/UTC--2022-10-03T11-17-14.433341409Z--1140a1bba2b535bcceb340264d91fdc8f8947325";
//        Credentials credentials = WalletUtils.loadCredentials("qwer1234", path);
////        System.out.println(credentials);
//        //发送交易
//        TransactionReceipt transactionReceipt = Transfer.sendFunds(web3j, credentials, myAddress, BigDecimal.valueOf(10.0), Convert.Unit.ETHER).send();
//        String transactionHash = transactionReceipt.getTransactionHash();
//        System.out.println(transactionHash);
//    }
//
//    @Resource
//    private ContractService contractService;
//
//    @Test
//    void deployEcommerce() throws Exception {
//        contractService.createAccount();
//    }
//
//    @Test
//    void name() {
//        Class c = ProductVo.class;
//        for (Field f : c.getDeclaredFields()) {
//            if (f.isAnnotationPresent(CopySourceName.class)){
//                CopySourceName annotation = f.getAnnotation(CopySourceName.class);
//                System.out.println(f.getName() + "--->" + annotation.value());
//            }
//        }
//    }
//
//    @Test
//    void test21() throws ParseException {
//        Long l = 1664254549962L;
//        BigInteger i = BigInteger.valueOf(l);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String s =simpleDateFormat.format(new Date(Long.parseLong(String.valueOf(i))));
//        System.out.println(s);
//
//        String as = "2022-09-27 16:19:30";
//
//        Date date = new Date();
//        date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(as);
//        long time = date.getTime();
//        String s1 =simpleDateFormat.format(new Date(Long.parseLong(String.valueOf(time))));
//        System.out.println(time);
//        System.out.println(s1);
//
//    }
//
//    @Test
//    void test34() {
//        Bid bid = new Bid(BigInteger.valueOf(1), "8", "hello", BigInteger.valueOf(12));
//        String contractAddress = "0x8d063243193D429A3988316CB83dC4b04edFF775";
//        Credentials credentials = Credentials.create("193f9d950707033705d0b9fdbe7b79a5bc97c9c9827523a3a6cdaa5defd92bf8");
//        ContractGasProvider provider = new StaticGasProvider(BigInteger.valueOf(20000000000L), BigInteger.valueOf(6721975L));
//        EcommerceStore load = EcommerceStore.load(contractAddress, web3j, credentials, provider);
////        BigInteger weiValue = BigInteger.valueOf(Long.parseLong(String.valueOf(Convert.toWei(String.valueOf(bid.getWeiValue()), Convert.Unit.ETHER))));
////        System.out.println(weiValue);
//        try {
//            TransactionReceipt transactionReceipt = load.bid(bid.getProductId(), bid.getAmount(), bid.getSecret(), bid.getWeiValue()).send();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;
//
//    @Test
//    void name44() {
//        String s = jwtTokenUtil.generateToken("21");
//        System.out.println(s);
//    }


//    @Test
//    void name56() {
//        Credentials credentials = Credentials.create("11bc7e2094bfcdf33b963c4ce8691d92def54cfd26b195a28bb4ab301c77b32b");
//        System.out.println(credentials);
//        System.out.println(credentials.getAddress());
//    }


    @Test
    void name121() throws ParseException {
//        BigInteger bigInteger = DateUtils.dateToTimestamp("2022-12-11 12:11:10");
//        System.out.println(bigInteger);
        String s = DateUtils.timestampToDate(BigInteger.valueOf(1670731870));
        System.out.println(s);
    }
}
