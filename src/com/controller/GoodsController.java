package com.controller;

import com.entity.Goods;
import com.entity.GoodsType;
import com.entity.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.GoodsService;
import com.service.impl.GoodsServiceImpl;
import com.utils.Constants;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
@WebServlet("/goods")
public class GoodsController extends BaseController {
    private static GoodsService service = new GoodsServiceImpl();
    public String getGoods(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        String typeId = request.getParameter("typeId");
        String pageIndex = request.getParameter("pageIndex");
        if (pageIndex == null) {
            pageIndex = "1";
        }
        Page page = new Page(Integer.valueOf(pageIndex));
        List<Goods> goodsList = null;
        goodsList = service.getByTypeId(Integer.valueOf(typeId),page);
        //ObjectMapper objectMapper = new ObjectMapper();
        //String str = objectMapper.writeValueAsString(goodsList);
        if (goodsList != null) {
            request.setAttribute("gList",goodsList);
            request.setAttribute("page",page);
            return Constants.FORWARD+"/goodsList.jsp";
        }else{
            request.setAttribute("glist","");
            return Constants.FORWARD+"/goodsList.jsp";
        }
    }
    public String getGoodsById(HttpServletRequest request,HttpServletResponse response){
        String id = request.getParameter("id");
        //System.out.println(id);
        Goods goods = service.getGoodsById(Integer.valueOf(id));
        //System.out.println(goods);
        if(goods != null) {
            request.setAttribute("goods",goods);
            return Constants.FORWARD+"/goodsDetail.jsp";
        }else{
            request.setAttribute("goods","");
            return Constants.FORWARD+"/goodsDetail.jsp";
        }

    }
}
