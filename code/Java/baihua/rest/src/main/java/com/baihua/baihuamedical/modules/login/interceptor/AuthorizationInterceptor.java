package com.baihua.baihuamedical.modules.login.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.baihua.baihuamedical.common.configs.ConfigProperties;
import com.baihua.baihuamedical.common.enums.Constants;
import com.baihua.baihuamedical.common.exception.AccountException;
import com.baihua.baihuamedical.common.exception.UnauthorizedException;
import com.baihua.baihuamedical.common.utils.DateUtils;
import com.baihua.baihuamedical.modules.login.annotation.LoginIgnore;
import com.baihua.baihuamedical.modules.user.dao.UsAccountDao;
import com.baihua.baihuamedical.modules.user.dao.UsTokenDao;
import com.baihua.baihuamedical.modules.user.entity.UsAccountEntity;
import com.baihua.baihuamedical.modules.user.entity.UsTokenEntity;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * 权限(Token)验证
 * @author zhaodongdong
 * @email 2977260348@qq.com
 * @date 2017-03-23 15:38
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private ConfigProperties configProperties;

    @Autowired
    private UsAccountDao usAccountDao;

    @Autowired
    private UsTokenDao tokenDao;

    public static final String ACCOUNT_KEY = "ACCOUNT";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LoginIgnore loginIgnore = null;
        if(handler instanceof HandlerMethod) {
            loginIgnore = ((HandlerMethod) handler).getMethodAnnotation(LoginIgnore.class);
        }

        if(loginIgnore != null){
            return true;
        }

        //获取用户凭证
        String token = request.getHeader(configProperties.getTokenId());
        if(StringUtils.isBlank(token)){
            token = request.getParameter(configProperties.getTokenId());
        }

        //凭证为空
        if(StringUtils.isBlank(token)){
            throw new UnauthorizedException("令牌为空");
        }

        LambdaQueryWrapper<UsTokenEntity> tokenQueryWrapper = new QueryWrapper<UsTokenEntity>().lambda();
        tokenQueryWrapper.eq(UsTokenEntity::getToken,token);
        UsTokenEntity usTokenEntity = tokenDao.selectOne(tokenQueryWrapper);

        Date now = new Date();
        if(null == usTokenEntity || usTokenEntity.getExpireTime().before(now)){
            throw new UnauthorizedException("令牌已失效，请重新登陆");
        }

        LambdaQueryWrapper<UsAccountEntity> accountQueryWrapper = new QueryWrapper<UsAccountEntity>().lambda();
        accountQueryWrapper.eq(UsAccountEntity::getId,usTokenEntity.getAccountId());
        UsAccountEntity accountEntity = usAccountDao.selectOne(accountQueryWrapper);

        if(accountEntity == null){
            throw new UnauthorizedException("令牌已失效，请重新登陆");
        }

        if(accountEntity.getStatus().intValue() == Constants.AccountStatus.freeze.getCode()){
            throw new AccountException("该用户已冻结");
        }

        if(accountEntity.getStatus().intValue() == Constants.AccountStatus.waitactive.getCode()){
            throw new AccountException("该用户未激活");
        }

        usTokenEntity.setLastTime(now);
        usTokenEntity.setExpireTime(DateUtils.addDateDays(now,configProperties.getTokenExpireTime()));
        tokenDao.updateById(usTokenEntity);
        request.setAttribute(ACCOUNT_KEY,accountEntity);
        return true;
    }
}
