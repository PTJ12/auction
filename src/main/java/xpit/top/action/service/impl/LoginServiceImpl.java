package xpit.top.action.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.WalletUtils;
import xpit.top.action.domain.ResponseResult;
import xpit.top.action.entity.Admin;
import xpit.top.action.enums.AppHttpCodeEnum;
import xpit.top.action.mapper.AdminMapper;
import xpit.top.action.service.LoginService;

import java.io.File;
import java.util.Objects;

/**
 * @author Pu Tongjiao
 * @date 2022/9/29 13:53
 */
@Service(value = "loginService")
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public ResponseResult login(Admin admin) {
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getUsername, admin.getUsername());
        Admin user = adminMapper.selectOne(queryWrapper);
        return ResponseResult.okResult(user);
    }

    @Override
    public ResponseResult register(Admin admin) {
        //如果账户已经注册 提示
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getUsername, admin.getUsername());
        Admin user = adminMapper.selectOne(queryWrapper);
        if (!Objects.isNull(user)){
            return ResponseResult.errorResult(AppHttpCodeEnum.USERNAME_EXIST);
        }
        //根据密码创建account
        try {
            ECKeyPair ecKeyPair = Keys.createEcKeyPair();//调用Keys的静态方法创建密钥对
            String privateKey = ecKeyPair.getPrivateKey().toString(16);//获取私钥
            String publicKey = ecKeyPair.getPublicKey().toString(16);//获取公钥
            String address = Keys.getAddress(publicKey);//获取地址值
            admin.setPrivateKey(privateKey);
            admin.setPublicKey(publicKey);
            admin.setAddress(address);

        } catch (Exception e) {
            e.printStackTrace();
        }
        adminMapper.insert(admin);
        return ResponseResult.okResult();
    }
}
