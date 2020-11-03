package com.controller;

import com.utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;


@WebServlet(name = "BaseContoller")
public class BaseController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        Class aClass = this.getClass();
        try {
            Method method1 = aClass.getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            String result = (String)method1.invoke(this, request, response);
            if(result!=null &&result.trim().length()!=0){
                if(result.startsWith(Constants.FORWARD)){
                    String path = result.substring(result.indexOf(":") + 1);
                    request.getRequestDispatcher(path).forward(request,response);
                }else if(result.startsWith(Constants.REDIRECT)){
                    String path = result.substring(result.indexOf(":") + 1);
                    response.sendRedirect(request.getContextPath()+path);//   项目名/registerSuccess.jsp
                }else{
                    response.getWriter().print(result);
                }
            }
        } catch (Exception e) {
            System.out.println("反射获取方法时出现异常");
            e.printStackTrace();
        }
    }
}
