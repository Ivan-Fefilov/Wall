package com.wall.core;

import com.wall.core.binding.Param;
import com.wall.models.AppUser;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class AbstractServlet extends HttpServlet {

    private static String SERVLETS_ROOT = "WEB-INF/pages/%s.jsp";

    private HttpServletRequest request;
    private HttpServletResponse response;

    private void init(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");  
        this.request = request;
        this.response = response;
        try {
            bindParameters();
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace(System.err);
        }
        
    }

    protected void forward(String path) throws ServletException, IOException {
        path = String.format(SERVLETS_ROOT, path);
        request.getRequestDispatcher(path).forward(request, response);
    }

    protected void redirect(String address) throws IOException {
        response.sendRedirect(address);
    }

    protected void setAttribute(String name, Object value) {
        request.setAttribute(name, value);
    }

    protected Object getAttribute(String name) {
        return request.getAttribute(name);
    }

    private Object marshal(String value, Class cls) {
        if (value == null || cls.equals(String.class)) {
            return value;
        } else if (cls.equals(int.class)) {
            return Integer.valueOf(value);
        } else if (cls.equals(double.class)) {
            return Double.valueOf(value);
        } else if (cls.equals(boolean.class)) {
            return Boolean.valueOf(value);
        }
        throw new UnsupportedOperationException();
    }

    private void bindParameters() throws IllegalArgumentException, IllegalAccessException {
        for (Field field : getClass().getDeclaredFields()) {
            Param param = field.getAnnotation(Param.class);
            if (param != null) {
                String name = param.value();
                if (name.length() == 0) {
                    name = field.getName();
                }
                Object value = request.getParameter(name);
                if (value != null) {
                    value = marshal((String) value, field.getType());
                    field.setAccessible(true);
                    field.set(this, value);
                }
            }
        }
    }

    protected void error(String message) throws ServletException, IOException {
        request.setAttribute("error", message);
        Map<String, String[]> params = request.getParameterMap();
        for (String param : params.keySet()) {
            request.setAttribute(param, request.getParameter(param));
        }
        doGet();
    }

    protected HttpSession getSession() {
        return request.getSession();
    }

    protected void save(AppUser user) {
        getSession().setAttribute("user", user);
    }

    protected void endSession() {
        getSession().removeAttribute("user");
    }

    protected boolean isAuth() {
        Boolean flag = (Boolean) getSession().getAttribute("auth");
        return flag != null && flag;
    }

    protected void doGet() throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        init(request, response);
        doGet();
    }

    protected void doPost() throws ServletException, IOException {
        super.doPost(request, response); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        init(request, response);
        doPost();
    }
}
