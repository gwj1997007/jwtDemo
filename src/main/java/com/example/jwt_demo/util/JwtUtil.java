package com.example.jwt_demo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.jwt_demo.constant.Constant;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.StringUtils;

import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;

/**
 * @Description: java类作用描述
 * @Author: gwj
 * @CreateDate: 2020/12/7 13:21
 * @UpdateUser: gwj
 * @UpdateDate: 2020/12/7 13:21
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
    public class JwtUtil {
    public static final long EXPIRATION_TIME = 3600_000_000L; // 1000 hour
    static final String SECRET = "ThisIsASecret";//密钥
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

        /**
         * 校验token是否正确
         * @param token
         * @param username
         * @return
         */
        public static boolean verifyToken(String token, String username){
            // 根据密码生成JWT校验器
            try{
                Algorithm algorithm = Algorithm.HMAC256(SECRET);
                JWTVerifier verifier = JWT.require(algorithm)
                        .withClaim("username",username)
                        .build();
                // 校验token，这里是jwt的内部实现，可能会抛出错误（token错误或过期等），同样将错误抛回给上层
                DecodedJWT jwt = verifier.verify(token);
                return true;
            }catch (Exception e){
                return false;
            }
        }

        /**
         * 获得token中的用户信息，无需解密
         * @param token
         * @return
         */
        public static String getUsername(String token){
            try{
                DecodedJWT jwt = JWT.decode(token);
                return jwt.getClaim("username").asString();
            }catch (JWTDecodeException e){
                return null;
            }
        }
        /**
         * 获得token中的用户信息，无需解密
         * @param token
         * @return
         */
        public static String getPassword(String token){
            try{
                DecodedJWT jwt = JWT.decode(token);
                return jwt.getClaim("password").asString();
            }catch (JWTDecodeException e){
                return null;
            }
        }

        /**
         * 生成签名
         * @return
         */
    public static String generateToken(String username,String password,Date generateTime) {
        HashMap<String, Object> map = new HashMap<>();
        //可以把任何安全的数据放到map里面，加密生成
        map.put("username", username);//用户名无法排除存在同名的问题
        map.put("password",password);
        map.put("generateTime",generateTime);
        String jwt = Jwts.builder()
                .setClaims(map)//将需要生成token的参数放在这个方法参数中
                .setExpiration(new Date(generateTime.getTime() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)//选择加密方式
                .compact();
        return jwt;//产生token
    }

        /**
         * 从cookie中获取token
         * @param httpServletRequest
         * @return
         */
        public static String getRequestToken(HttpServletRequest httpServletRequest){
            String token = "";
            Cookie[] cookies = httpServletRequest.getCookies();
            if(cookies != null){
                for(Cookie ck : cookies){
                    if(StringUtils.equals(Constant.COOKIE_TOKEN_NAME, ck.getName())){
                        token = ck.getValue();
                        break;
                    }
                }
            }
            return token;
        }

        /**
         * 编辑浏览器cookie
         * @param response
         * @param tokenValue
         */
        public static void editCookieToken(ServletResponse response, String tokenValue){
            HttpServletResponse httpServletResponse = (HttpServletResponse)response;
            Cookie cookie = new Cookie(Constant.COOKIE_TOKEN_NAME, tokenValue);
            cookie.setPath("/");
            cookie.setHttpOnly(true);//前端不可读cookie
            //跨域向前端写cookie
            httpServletResponse.setHeader("Access-Control-Allow-Origin",
                    httpServletResponse.getHeader("Origin"));
            httpServletResponse.addCookie(cookie);
        }

}
