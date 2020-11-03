package com.controller.admin.user;

import com.controller.BaseController;
import com.entity.GoodsType;
import com.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.GoodsTypeService;
import com.service.UserService;
import com.service.impl.GoodsTypeServiceImpl;
import com.service.impl.UserServiceImpl;
import com.utils.Base64Utils;
import com.utils.Constants;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/user")
public class AdminUserController extends BaseController {
    private static UserService service = new UserServiceImpl();
    //登录
    public String adminLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User loginAdmin = service.login(username, password);
        System.out.println(loginAdmin);
        if (loginAdmin != null && loginAdmin.getFlag() == 1 && loginAdmin.getRole()==0) {
            request.getSession().setAttribute("admin",loginAdmin);
            System.out.println("管理员登录成功");
            return Constants.REDIRECT+"/admin/admin.jsp";
            //response.sendRedirect("index.jsp");
        }else{
            System.out.println("管理员登录失败");
            return Constants.REDIRECT+"/admin/login.jsp";
        }
    }
    //注销
    public String adminLogOut(HttpServletRequest request,HttpServletResponse response){
        request.getSession().removeAttribute("admin");
        return Constants.REDIRECT+"/admin/login.jsp";
    }
    //会员用户管理
    public String getUserList(HttpServletRequest request,HttpServletResponse response) throws IOException{
        List<User> users = service.getUsers();
        if (users != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            String s = objectMapper.writeValueAsString(users);
            return s;
        }else {
            return "";
        }
    }
    //条件查询
    public String searchUser(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String username = request.getParameter("username");
        String gender = request.getParameter("gender");
        List<User> users = null;
        users = service.getUsers(username,gender);
        if (users != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            String s = objectMapper.writeValueAsString(users);
            return s;
        }else {
            return "";
        }
    }
    //删除用户
    public String deleteUser(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String id = request.getParameter("id");
        int sign = service.deleteUser(Integer.valueOf(id));
        return null;
    }
}
