package com.wall.web.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = "/*")
public class UriFilter implements Filter {

    private String[] paths = {
        "/settings",
        "/edit",
        "/delete"
    };

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    private boolean isSignedIn(HttpSession session) {
        return session.getAttribute("user") != null;
    }

    private boolean isAuthorizedAccess(HttpServletRequest request) {
        String context = request.getContextPath();
        String path = request.getRequestURI().substring(context.length());
        for (String item : paths) {
            if (item.equals(path)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (!isSignedIn(req.getSession()) && isAuthorizedAccess(req)) {
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.sendRedirect("/login");
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
