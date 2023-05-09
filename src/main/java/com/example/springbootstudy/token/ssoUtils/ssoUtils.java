package com.example.springbootstudy.token.ssoUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

/**
 * Description:Token生成工具
 * 第一部分我们称它为头部（header),第二部分我们称其为载荷（payload, 类似于飞机上承载的物品)，第三部分是签证（signature).
 * Auth: Frank
 * Date: 2017-11-02
 * Time: 下午 5:05
 */
public class ssoUtils {

    private static final  String SECRET="471e4b34-2990-43bc-ab41-12c22411f49a";

    private static Logger log = LogManager.getLogger(ssoUtils.class);

    public static final  Integer EXPIRES_TIME = 6000000;//token过期时间

    public static final  String BEARER = "bearer";

    /**
     * @Description:
     * @author wangfan <wangfan_paas@si-tech.com.cn>
     * @date 2020/10/13 15:47
     * @version: V1.0
     *
     * @param
     * @return void
     * @throws
     **/
    public static String createToken(Map<String,Object> mapParam) {
        //签发时间
        Date iatDate = new Date();
        //过期时间设置为1分钟
        Calendar nowTime= Calendar.getInstance();
        nowTime.add(Calendar.MILLISECOND,EXPIRES_TIME);
        Date expiresDate= nowTime.getTime();

        Map<String,Object>  map = new HashMap<String,Object>();
        map.put("alg","HS256");
        map.put("typ","JWT");
        map.put("client_secret",mapParam.get("client_secret"));
        map.put("scope",mapParam.get("scope"));
        if (mapParam.get("phone") != null){
            map.put("phone",mapParam.get("phone"));
        }
        JWTCreator.Builder builder = JWT.create().withHeader(map);
        for(String key:map.keySet()){
            String value=map.get(key).toString();
            builder.withClaim(key,value);
        }
        builder.withExpiresAt(expiresDate);
        builder.withIssuedAt(iatDate);
        try {
            String token=builder.sign(Algorithm.HMAC256(mapParam.get("client_secret").toString()+SECRET));
            return token;
        }catch (Exception e){
            return "";
        }
    }

    /**
     * @Description: 解密token
     * @author wangfan <wangfan_paas@si-tech.com.cn>
     * @date 2020/10/13 17:28
     * @version: V1.0
     *
     * @param
     * @return void
     * @throws
     **/
    public static boolean verifyToken(String client_secret_server,String authorization) throws Exception{

        DecodedJWT jwt=null;
        String token="";
        Boolean result=true;
        if(authorization.contains("Bearer")){
            token=authorization.replace("Bearer","").trim();
        }else if(authorization.contains("bearer")){
            token=authorization.replace("bearer","").trim();
        }
        JWTVerifier require = JWT.require(Algorithm.HMAC256(client_secret_server+SECRET)).build();
        try{
            jwt = require.verify(token);
            Map<String, Claim> claims = jwt.getClaims();
            log.info("签名解析secret::::client_secret============"+claims.get("client_secret").asString());
            log.info("签名解析scope::::scope============"+claims.get("scope").asString());
            //log.info("签名解析phone::::phone============"+claims.get("phone").asString());
            if (claims.get("phone") != null){
                log.info("签名解析phone::::phone============"+claims.get("phone").asString());
            }
            log.info("服务配置secret::::client_secret_server============"+client_secret_server);
        }catch (Exception e){
            log.info("check token exception!");
            result = false;
        }
        return  result;
    }
    public static void main(String[] args) throws Exception {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("client_secret","海达保险");
        map.put("scope","read");
        //map.put("phone","12345");
        String token = createToken(map);
        System.out.println(token);

        JWTVerifier require = JWT.require(Algorithm.HMAC256("海达保险"+SECRET)).build();
        DecodedJWT jwt = require.verify(token);
        Map<String, Claim> claims = jwt.getClaims();
        if (claims.get("phone") != null){
            String phone = claims.get("phone").asString();
            System.out.println("phone ="+phone);
        }


        System.out.println(verifyToken("海达保险","Bearer"+token));


    }
}
