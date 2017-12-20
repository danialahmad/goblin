package com.fourninja.goblin.config.multi;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class MultiTenantFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        if (req.getHeader(MultiTenantConstants.TENANT_KEY) != null) {
            req.setAttribute(MultiTenantConstants.CURRENT_TENANT_IDENTIFIER, req.getHeader(MultiTenantConstants.TENANT_KEY));
        } else {
            req.setAttribute(MultiTenantConstants.CURRENT_TENANT_IDENTIFIER, MultiTenantConstants.DEFAULT_TENANT_ID);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {}
}
