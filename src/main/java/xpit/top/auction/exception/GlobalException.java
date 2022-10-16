package xpit.top.auction.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xpit.top.auction.domain.ResponseResult;
import xpit.top.auction.enums.AppHttpCodeEnum;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @author PuTongjiao
 * @date 2022/7/19 23:02
 */
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(SQLException.class)
    public ResponseResult mySqlException(SQLException e){
        if (e instanceof SQLIntegrityConstraintViolationException){
            return ResponseResult.errorResult(AppHttpCodeEnum.SQL_Integrity);
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.SQL_ERROR);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseResult myExpiredJwtException(ExpiredJwtException e){
        return ResponseResult.errorResult(AppHttpCodeEnum.JWT_EXPIRED);
    }
}