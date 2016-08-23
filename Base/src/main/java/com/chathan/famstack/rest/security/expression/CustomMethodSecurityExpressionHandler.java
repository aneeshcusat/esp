package com.chathan.famstack.rest.security.expression;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;

public class CustomMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {

    private final AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();

    @Autowired
    private AutowireCapableBeanFactory autowireBeanFactory;

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation) {
        CustomSecurityExpressionRoot securityExpression = new CustomSecurityExpressionRoot(authentication);

        autowireBeanFactory.autowireBean(securityExpression);

        securityExpression.setTrustResolver(trustResolver);
        securityExpression.setThis(invocation.getThis());
        securityExpression.setPermissionEvaluator(getPermissionEvaluator());
        return securityExpression;
    }

}
