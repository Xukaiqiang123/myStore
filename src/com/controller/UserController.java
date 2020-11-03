package com.controller;

import com.entity.User;
import com.service.UserService;
import com.service.impl.UserServiceImpl;
import com.utils.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/user")
public class UserController extends BaseController {
    private static UserService service = new UserServiceImpl();
    //登录
    public String userLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String auto = request.getParameter("auto");
        User loginUser = service.login(username, password);
        if (loginUser != null && loginUser.getFlag() == 1) {
            if (auto != null) {
                Cookie cookie = new Cookie("userInfo", Base64Utils.encode(username+"#"+password));
                cookie.setPath("/MyShop");
                cookie.setMaxAge(60 * 60 * 24 * 14);
                response.addCookie(cookie);
            }
            request.getSession().setAttribute("loginUser",loginUser);
            return Constants.REDIRECT+"/index.jsp";
            //response.sendRedirect("index.jsp");
        }else{
            request.setAttribute("msg","用户名或密码错误或未激活");
            return Constants.FORWARD+"login.jsp";
        }
    }
    //注册用户
    public String userRegister(HttpServletRequest request,HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        User user = new User(username,password,email,gender, UserNumUtils.USER_NOT_ACTIVE,UserNumUtils.USER_ROLE, RandomUtils.createActive());
        System.out.println(user);
        int sign = service.register(user);
        if (sign == 0) {
            request.setAttribute("registerSign","注册失败");
            return Constants.FORWARD+"register.jsp";
            //request.getRequestDispatcher("register.jsp").forward(request,response);
        }else{
            EmailUtils.sendEmail(user);
            return Constants.REDIRECT+"/registerSuccess.jsp";
            //response.sendRedirect("index.jsp");
        }
    }
    //检查注册用户名
    public String checkUsername(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String username = request.getParameter("username");
        User user = service.checkName(username);
        System.out.println("检查注册名/登录名："+user);
        PrintWriter writer = response.getWriter();
        if (user != null) {
            return "1";
        }else{
            return "0";
        }
    }
    //激活
    public String active(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String code = request.getParameter("code");
        email = Base64Utils.decode(email);
        code = Base64Utils.decode(code);
        UserService userService = new UserServiceImpl();
        int i = userService.activeUser(email, code);
        if(i>0){
            request.setAttribute("msg", "激活成功！");
        }else{
            request.setAttribute("msg", "激活失败！");
        }
        return Constants.FORWARD+"message.jsp";
    }
    //注销
    public String logOut(HttpServletRequest request,HttpServletResponse response){
        request.getSession().removeAttribute("loginUser");
        Cookie cookie = new Cookie("userInfo","");
        cookie.setPath("/MyShop");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        request.setAttribute("msg", "注销成功！");
        return Constants.FORWARD+"login.jsp";
    }
}
