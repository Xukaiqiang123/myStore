package com.filter;

import com.entity.User;
import com.service.UserService;
import com.service.impl.UserServiceImpl;
import com.utils.Base64Utils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/login.jsp")
public class AutoLoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("userInfo")){
                    System.out.println(cookie.getName()+cookie.getValue());
                    String value = Base64Utils.decode(cookie.getValue());
                    String[] split = value.split("#");
                    UserService userService = new UserServiceImpl();
                    User user = userService.login(split[0], split[1]);
                    if(user!=null){
                        request.getSession().setAttribute("loginUser", user);
                        response.sendRedirect(request.getContextPath()+"/index.jsp");
                    }else{
                        chain.doFilter(req, resp);
                    }
                }
            }
            chain.doFilter(req, resp);
        }else{
            chain.doFilter(req, resp);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
