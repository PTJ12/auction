package xpit.top.action.config.securiy.filter;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import xpit.top.action.constants.SystemConstants;
import xpit.top.action.domain.ResponseResult;
import xpit.top.action.entity.LoginUser;
import xpit.top.action.enums.AppHttpCodeEnum;
import xpit.top.action.utils.JwtTokenUtil;
import xpit.top.action.utils.RedisCache;
import xpit.top.action.utils.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author PuTongjiao
 * @date 2022/8/21 12:51
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取请求头中的token
        String authHeader = request.getHeader(tokenHeader);

//        String token = request.getHeader("token");
        if (!StringUtils.hasText(authHeader)){
            //说明该接口不需要登录 直接放行
            filterChain.doFilter(request, response);
            return;
        }
        //解析获取userid
        String userId = null;
        try {
            String authToken = authHeader.substring(tokenHead.length());
            userId = jwtTokenUtil.getUserIdFromToken(authToken);
        } catch (Exception e) {
            e.printStackTrace();
            //token超时 token非法
            //响应到前端需要重新登录
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }
        //从redis中获取用户信息
        LoginUser loginUser = redisCache.getCacheObject(SystemConstants.REDIS_KEY + userId);
        //如果loginUser为null
        if (Objects.isNull(loginUser)){
            //说明登录过期 重新登录
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }
        //存入SecurityContextHeader
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
