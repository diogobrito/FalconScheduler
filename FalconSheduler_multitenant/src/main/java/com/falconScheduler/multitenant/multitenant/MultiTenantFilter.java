package com.falconScheduler.multitenant.multitenant;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import static com.falconScheduler.multitenant.multitenant.MultiTenantConstants.*;

import java.io.IOException;

public class MultiTenantFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        if (req.getHeader(TENANT_KEY) != null) {
            req.setAttribute(CURRENT_TENANT_IDENTIFIER, req.getHeader(TENANT_KEY));
        } else {
            req.setAttribute(CURRENT_TENANT_IDENTIFIER, DEFAULT_TENANT_ID);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {}
}
