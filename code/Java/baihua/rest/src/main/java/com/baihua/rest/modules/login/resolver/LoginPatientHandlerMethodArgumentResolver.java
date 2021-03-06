package com.baihua.rest.modules.login.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.baihua.core.common.enums.Constants;
import com.baihua.core.common.exception.ParameterException;
import com.baihua.rest.modules.login.annotation.LoginPatient;
import com.baihua.rest.modules.login.interceptor.AuthorizationInterceptor;
import com.baihua.core.modules.user.dao.UsPatientDao;
import com.baihua.core.modules.user.entity.UsAccountEntity;
import com.baihua.core.modules.user.entity.UsPatientEntity;

/**
 * 有@LoginUser注解的方法参数，注入当前登录用户
 * @author zhaodongdong
 * @email 2977260348@qq.com
 * @date 2017-03-23 22:02
 */
@Component
public class LoginPatientHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private UsPatientDao usPatientDao;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(UsPatientEntity.class) && parameter.hasParameterAnnotation(LoginPatient.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) throws Exception {
        UsAccountEntity accountEntity = (UsAccountEntity)request.getAttribute(AuthorizationInterceptor.ACCOUNT_KEY, RequestAttributes.SCOPE_REQUEST);
        if(null != accountEntity && accountEntity.getType().intValue() == Constants.AccountType.patient.getCode()){
            return usPatientDao.selectById(accountEntity.getSId());
        }
        throw new ParameterException("参数异常");
    }
}
