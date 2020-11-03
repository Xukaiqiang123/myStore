package com.controller;

import com.entity.Orders;
import com.entity.User;
import com.entity.WeiXin;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.OrderService;
import com.service.impl.OrderServiceImpl;
import com.utils.Constants;
import com.utils.RandomUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/orders")
public class OrderController extends BaseController {
    private static OrderService service = new OrderServiceImpl();
    //添加订单
    public String addOrders(HttpServletRequest request, HttpServletResponse response) {
        Integer aId = Integer.valueOf(request.getParameter("aId"));
        Integer uId = Integer.valueOf(request.getParameter("uId"));
        Integer sum = Integer.valueOf(request.getParameter("sum"));
        Orders orders = new Orders(RandomUtils.createOrderId(),uId,sum,1,aId,new Date());
        service.addOrdersOne(orders);
        //return null;
        return Constants.REDIRECT+"/orders?method=getAllOrders";
    }
    //查看所有订单
    public String getAllOrders(HttpServletRequest request, HttpServletResponse response) {
        User loginUser = (User)request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            request.setAttribute("msg", "查看订单请先登录");
            return Constants.FORWARD+"/login.jsp";
        }
        Integer uId = loginUser.getId();
        List<Orders> ordersList = service.lookAllOrders(uId);
        request.setAttribute("orderList",ordersList);
        return Constants.FORWARD+"/orderList.jsp";
    }
    //查看订单详情
    public String getOrderDetail(HttpServletRequest request, HttpServletResponse response){
        String oId = request.getParameter("oId");
        Orders ordersList = service.lookOrdersDetail(oId);
        request.setAttribute("od",ordersList);
        return Constants.FORWARD+"/orderDetail.jsp";
    }
    //微信支付
    public String success(HttpServletRequest request, HttpServletResponse response){
        String oId = request.getParameter("oid");
        String result = request.getParameter("result");
        try {
            //2.解析JSON数据封装成WeiXin
            ObjectMapper objectMapper = new ObjectMapper();
            WeiXin weiXin = objectMapper.readValue(result, WeiXin.class);
            String result_code = weiXin.getResult().getResult_code();
            //支付结果判断
            if(result_code !=null && result_code.equals("SUCCESS")){
                //支付成功
                //修改订单状态。
                int i = service.updateStatus(oId);
                return Constants.REDIRECT+"/orders?method=getAllOrders";
            }else{
                //支付失败
                request.setAttribute("msg", "支付失败！");
                return Constants.FORWARD+"/message.jsp";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    //确认收货
    public String changeStatus(HttpServletRequest request, HttpServletResponse response){
        String oId = request.getParameter("oId");
        service.changeStatus(oId);
        return Constants.FORWARD+"/orders?method=getAllOrders";
    }

}
