package com.controller;

import com.entity.AddressInfo;
import com.entity.User;
import com.service.AddressService;
import com.service.impl.AddressServiceImpl;
import com.utils.Constants;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/address")
public class AddressController extends BaseController {
    private static AddressService service = new AddressServiceImpl();
    //获得收获地址
    public String getAllAddress(HttpServletRequest request, HttpServletResponse response){
        User loginUser = (User)request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            request.setAttribute("msg", "查看收货地址请先登录");
            return Constants.FORWARD+"/login.jsp";
        }
        Integer id = loginUser.getId();
        List<AddressInfo> addList = service.getAddressByUid(id);
        //System.out.println(addList);
        request.setAttribute("addList",addList);
        return Constants.FORWARD+"/self_info.jsp";
    }
    //添加收获地址
    public String addAddress(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String detail = request.getParameter("detail");
        String uId = request.getParameter("uid");
        AddressInfo addressInfo = new AddressInfo(detail,name,phone,0,Integer.valueOf(uId));
        System.out.println(addressInfo);
        service.addAddress(addressInfo);
        return Constants.REDIRECT+"/address?method=getAllAddress";
    }
    //设置默认地址
    public String setDefault(HttpServletRequest request, HttpServletResponse response){
        User loginUser = (User)request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            request.setAttribute("msg", "设置默认地址请先登录");
            return Constants.FORWARD+"/login.jsp";
        }
        Integer uId = loginUser.getId();
        String id = request.getParameter("id");
        service.updateAddress(Integer.valueOf(id),uId);
        return Constants.REDIRECT+"/address?method=getAllAddress";
    }
    //删除地址
    public String deleteAddress(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        service.deleteAddress(Integer.valueOf(id));
        return Constants.REDIRECT+"/address?method=getAllAddress";
    }
    //修改地址
    public String updateAddress(HttpServletRequest request, HttpServletResponse response){
        User loginUser = (User)request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            request.setAttribute("msg", "修改收货地址请先登录");
            return Constants.FORWARD+"/login.jsp";
        }
        Integer uId = loginUser.getId();
        Integer id = Integer.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        String detail = request.getParameter("detail");
        String phone = request.getParameter("phone");
        Integer level = Integer.valueOf(request.getParameter("level"));
        AddressInfo addressInfo = new AddressInfo(id,detail,name,phone,level,uId);
        service.updateAddress(addressInfo);
        return Constants.REDIRECT+"/address?method=getAllAddress";
    }
}
