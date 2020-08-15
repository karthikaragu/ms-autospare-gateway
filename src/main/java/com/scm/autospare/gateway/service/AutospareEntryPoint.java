package com.scm.autospare.gateway.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AutospareEntryPoint implements AuthenticationEntryPoint {
    private static final Log logger = LogFactory.getLog(AutospareEntryPoint.class);

    public AutospareEntryPoint() {
    }

    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2) throws IOException {
        if (logger.isDebugEnabled()) {
            logger.debug("Pre-authenticated entry point called. Rejecting access");
        }

        if(arg2 instanceof InsufficientAuthenticationException){
            response.sendError(401, "User Account does not exists.Please register to login");
        }
        response.sendError(403, "Access Denied");
    }
}
