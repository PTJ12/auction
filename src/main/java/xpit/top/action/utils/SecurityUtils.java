package xpit.top.action.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import xpit.top.action.entity.LoginUser;

/**
 * @author PuTongjiao
 * @date 2022/8/22 18:43
 */
@Component
public class SecurityUtils {
    public LoginUser getLoginUser(){
        return ((LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    public Integer getUserId() {
        return getLoginUser().getAdmin().getId();
    }

    public String getPrivateKey() {
        return getLoginUser().getAdmin().getPrivateKey();

    }
 }
