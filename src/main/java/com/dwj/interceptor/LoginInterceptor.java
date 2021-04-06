package com.dwj.interceptor;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dwj.common.result.JsonResult;
import com.dwj.common.result.ResultCode;
import com.dwj.common.result.ResultTool;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //验证token
        String token = request.getHeader("token");
        JWTVerifier build = JWT.require(Algorithm.HMAC256("@#qwer")).build();
        DecodedJWT verify = null;
        try {
            if (!StringUtils.isEmpty(token)) {
                //有token值
                verify = build.verify(token);
                String username = verify.getClaim("username").asString();
                if (username != null) {
                    return true;
                }
            } else {
                //没有token值
                response.setContentType("application/json;charset=utf-8");
                PrintWriter writer = response.getWriter();
                JsonResult fail = ResultTool.fail(ResultCode.NO_PERMISSION);
                String s = JSON.toJSONString(fail);
                writer.write(s);
                writer.close();
            }
        } catch (TokenExpiredException e) {
            //超时
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            JsonResult fail = ResultTool.fail(ResultCode.USER_ACCOUNT_USE_BY_OTHERS);
            String s = JSON.toJSONString(fail);
            writer.write(s);
            writer.close();
        }catch (Exception e){
            //token值错误
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            JsonResult fail = ResultTool.fail(ResultCode.USER_NOT_LOGIN);
            String s = JSON.toJSONString(fail);
            writer.write(s);
            writer.close();
        }
        return false;
    }
}
