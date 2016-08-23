package com.famstack.esp.rest.security.login;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Scope(SCOPE_SINGLETON)
public class UserSecurityContextBinder {

    @Autowired
    private AuthenticationTrustResolver trustResolver;

    public void bindUserAuthentication(Authentication authentication) {
        getSecurityContext().setAuthentication(authentication);
    }

    public void unbindUserAuthentication() {
        getSecurityContext().setAuthentication(null);
    }

    public boolean isAuthenticated() {
        Authentication authentication = getSecurityContext().getAuthentication();
        return !trustResolver.isAnonymous(authentication) && authentication != null;
    }

    SecurityContext getSecurityContext() {
        return SecurityContextHolder.getContext();
    }

    public AuthenticationTrustResolver getTrustResolver() {
        return trustResolver;
    }

    public void setTrustResolver(AuthenticationTrustResolver trustResolver) {
        this.trustResolver = trustResolver;
    }
}
