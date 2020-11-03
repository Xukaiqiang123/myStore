package com.controller.admin.order;

import com.controller.BaseController;
import com.entity.Orders;
import com.service.OrderService;
import com.service.impl.OrderServiceImpl;
import com.utils.Constants;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/admin/orders")
public class AdminOrdersController extends BaseController {
    private static OrderService service = new OrderServiceImpl();
    //查看订单
    public String getAllOrders(HttpServletRequest request, HttpServletResponse response){
        List<Orders> allOrders = service.getAllOrders();
        request.setAttribute("orderList",allOrders);
        return Constants.FORWARD+"/admin/showAllOrder.jsp";
    }
    //管理员发货
    public String sendOrder(HttpServletRequest request, HttpServletResponse response){
        String oId = request.getParameter("oId");
        service.sendOrders(oId);
        return Constants.REDIRECT+"/admin/orders?method=getAllOrders";
    }
    //搜索订单
    public String searchOrder(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String status = request.getParameter("status");
        List<Orders> allOrders = service.searchOrder(username,status);
        request.setAttribute("orderList",allOrders);
        return Constants.FORWARD+"/admin/showAllOrder.jsp";
    }
}
