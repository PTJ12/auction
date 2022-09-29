package xpit.top.action.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import xpit.top.action.constants.SystemConstants;
import xpit.top.action.domain.ResponseResult;
import xpit.top.action.domain.vo.UserInfoVo;
import xpit.top.action.domain.vo.TokenVo;
import xpit.top.action.entity.Admin;
import xpit.top.action.entity.LoginUser;
import xpit.top.action.enums.AppHttpCodeEnum;
import xpit.top.action.mapper.AdminMapper;
import xpit.top.action.service.LoginService;
import xpit.top.action.utils.BeanCopyUtils;
import xpit.top.action.utils.JwtTokenUtil;
import xpit.top.action.utils.RedisCache;
import xpit.top.action.utils.SecurityUtils;

import java.util.Objects;

/**
 * @author Pu Tongjiao
 * @date 2022/9/29 13:53
 */
@Service(value = "loginService")
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private SecurityUtils securityUtils;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;


    @Override
    public ResponseResult login(Admin admin) {
        //用户名密码登录
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(admin.getUsername(), admin.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }
        //获取userid 生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        Integer userId = loginUser.getAdmin().getId();
        String token = jwtTokenUtil.generateToken(userId.toString());
        redisCache.setCacheObject(SystemConstants.REDIS_KEY + userId, loginUser);
        TokenVo tokenVo = new TokenVo(tokenHeader, tokenHead, token);
        return ResponseResult.okResult(tokenVo);
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
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminMapper.insert(admin);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult logout() {

        Integer userId = securityUtils.getUserId();
        redisCache.deleteObject(SystemConstants.REDIS_KEY + userId);
        return ResponseResult.okResult();
    }
}
