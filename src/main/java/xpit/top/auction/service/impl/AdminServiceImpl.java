package xpit.top.auction.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;
import xpit.top.auction.domain.ResponseResult;
import xpit.top.auction.domain.vo.UserInfoVo;
import xpit.top.auction.entity.Admin;
import xpit.top.auction.mapper.AdminMapper;
import xpit.top.auction.service.AdminService;
import xpit.top.auction.utils.BeanCopyUtils;
import xpit.top.auction.utils.SecurityUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * (Admin)表服务实现类
 *
 * @author PuTongjiao
 * @since 2022-09-29 14:14:28
 */
@Service("adminService")
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private SecurityUtils securityUtils;

    @Autowired
    private Web3j web3j;

    @Override
    public ResponseResult userInfo() {
        Admin admin = securityUtils.getLoginUser().getAdmin();
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(admin, UserInfoVo.class);
        return ResponseResult.okResult(userInfoVo);
    }


    @Override
    public ResponseResult transactionToUser(Double value, String address) throws Exception {
        Credentials credentials = Credentials.create(securityUtils.getPrivateKey());
        //发送交易
        TransactionReceipt transactionReceipt = Transfer.sendFunds(web3j, credentials, address, BigDecimal.valueOf(value), Convert.Unit.ETHER).send();
        String transactionHash = transactionReceipt.getTransactionHash();
        return ResponseResult.okResult(transactionHash);
    }

    @Override
    public ResponseResult getBalance() throws IOException {
        String address = securityUtils.getAddress();
        EthGetBalance ethGetBalance = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
        BigInteger balance = ethGetBalance.getBalance();
        return ResponseResult.okResult(Convert.fromWei(balance.toString(), Convert.Unit.ETHER));
    }

}

