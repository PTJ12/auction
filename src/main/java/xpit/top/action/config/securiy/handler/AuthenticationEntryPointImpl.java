package xpit.top.action.config.securiy.handler;

import com.alibaba.fastjson.JSON;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import xpit.top.action.domain.ResponseResult;
import xpit.top.action.enums.AppHttpCodeEnum;
import xpit.top.action.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败
 * @author PuTongjiao
 * @date 2022/8/22 14:06
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        authException.printStackTrace();
        ResponseResult responseResult = null;
        //InsufficientAuthenticationException

        //BadCredentialsException
        if (authException instanceof BadCredentialsException){
            responseResult = ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_ERROR.getCode(), authException.getMessage());
        }else if (authException instanceof InsufficientAuthenticationException){
            responseResult = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }else {
            responseResult = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(), "认证或授权失败");
        }
        //响应给前端
        WebUtils.renderString(response, JSON.toJSONString(responseResult));

    }
}
