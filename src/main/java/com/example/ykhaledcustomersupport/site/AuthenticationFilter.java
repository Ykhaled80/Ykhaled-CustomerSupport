package com.example.ykhaledcustomersupport.site;
import com.example.ykhaledcustomersupport.entities.UserPrincipal;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.security.Principal;

@WebFilter(value={"/", "/ticket/*", "/sessions"})
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest)servletRequest).getSession(false); // don't create a session if doesn't exit

        final Principal principal = UserPrincipal.getPrincipal(session);
        // no one logged so redirect
        if(principal == null ) {
            ((HttpServletResponse)servletResponse).sendRedirect(((HttpServletRequest)servletRequest).getContextPath() + "/login");
        }
        else {
            filterChain.doFilter(new HttpServletRequestWrapper((HttpServletRequest)servletRequest){
                //create  the session Principal
                @Override
                public Principal getUserPrincipal(){
                    return principal;}
                },servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
