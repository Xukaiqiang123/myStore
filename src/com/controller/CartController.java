package com.controller;

import com.entity.AddressInfo;
import com.entity.Cart;
import com.entity.User;
import com.service.AddressService;
import com.service.CartService;
import com.service.impl.AddressServiceImpl;
import com.service.impl.CartServiceImpl;
import com.utils.Constants;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/cart")
public class CartController extends BaseController {
    private CartService service = new CartServiceImpl();
    private AddressService addressService = new AddressServiceImpl();
    //加入购物车测
    public String addCart(HttpServletRequest request, HttpServletResponse response){
        User loginUser = (User)request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            request.setAttribute("msg", "添加购物车请先登录");
            return Constants.FORWARD+"/login.jsp";
        }
        Integer gId = Integer.valueOf(request.getParameter("gId"));
        Integer id = loginUser.getId();
        Integer price = Integer.valueOf(request.getParameter("price"));
        int sign = service.insertCart(gId, id, price);
        if (sign != 0) {
            return Constants.REDIRECT + "/cartSuccess.jsp";
        }else{
            return null;
        }
    }
    //查看购物车
    public String lookCart(HttpServletRequest request, HttpServletResponse response){
        User loginUser = (User)request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            request.setAttribute("msg", "添加购物车请先登录");
            return Constants.FORWARD+"/login.jsp";
        }
        Integer id = loginUser.getId();
        List<Cart> carts = service.getCartsById(id);
        if (carts != null) {
            request.setAttribute("carts",carts);
            return Constants.FORWARD + "/cart.jsp";
        }else{
            request.setAttribute("carts","");
            return Constants.FORWARD + "/cart.jsp";
        }
    }
    //从购物车删除
    public String deleteByGid(HttpServletRequest request,HttpServletResponse response){
        String gId = request.getParameter("gId");
        User loginUser = (User)request.getSession().getAttribute("loginUser");
        Integer id = loginUser.getId();
        service.deleteCart(Integer.valueOf(gId),id);
        return Constants.REDIRECT+"/cart?method=lookCart";
    }
    //清空购物车
    public String clearCart(HttpServletRequest request,HttpServletResponse response){
        User loginUser = (User)request.getSession().getAttribute("loginUser");
        Integer id = loginUser.getId();
        service.clearAllCart(id);
        return Constants.REDIRECT+"/cart?method=lookCart";
    }
    //修改购物车上品数量
    public String updateCartNum(HttpServletRequest request,HttpServletResponse response){
        User loginUser = (User)request.getSession().getAttribute("loginUser");
        Integer id = loginUser.getId();
        String gId = request.getParameter("gId");
        String num = request.getParameter("num");
        String price = request.getParameter("price");
        Cart cart = new Cart(id,Integer.valueOf(gId),Integer.valueOf(num),Double.valueOf(price));
        int sign = service.updateCart(cart);
        return null;
    }
    //从购物车添加收获地址
    public String getAddress(HttpServletRequest request,HttpServletResponse response){
        User loginUser = (User)request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            request.setAttribute("msg", "添加收获地址请先登录");
            return Constants.FORWARD+"/login.jsp";
        }
        Integer id = loginUser.getId();
        List<Cart> cartList = service.getCartsById(id);
        List<AddressInfo> addList = addressService.getAddressByUid(id);
        if (cartList != null) {
            request.setAttribute("cartList",cartList);
            request.setAttribute("addList",addList);
            return Constants.FORWARD + "/order.jsp";
        }else{
            request.setAttribute("cartList","");
            request.setAttribute("addList","");
            return Constants.FORWARD + "/order.jsp";
        }
    }
}
