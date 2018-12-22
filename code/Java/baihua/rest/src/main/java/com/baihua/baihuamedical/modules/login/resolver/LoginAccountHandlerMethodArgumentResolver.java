package com.baihua.baihuamedical.modules.login.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.baihua.baihuamedical.modules.login.annotation.LoginAccount;
import com.baihua.baihuamedical.modules.login.interceptor.AuthorizationInterceptor;
import com.baihua.baihuamedical.modules.user.entity.UsAccountEntity;

/**
 * 有@LoginUser注解的方法参数，注入当前登录用户
 * @author zhaodongdong
 * @email 2977260348@qq.com
 * @date 2017-03-23 22:02
 */
@Component
public class LoginAccountHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(UsAccountEntity.class) && parameter.hasParameterAnnotation(LoginAccount.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) throws Exception {
        return request.getAttribute(AuthorizationInterceptor.ACCOUNT_KEY, RequestAttributes.SCOPE_REQUEST);
    }
}
