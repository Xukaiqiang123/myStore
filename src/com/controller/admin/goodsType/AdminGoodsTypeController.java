package com.controller.admin.goodsType;

import com.controller.BaseController;
import com.entity.GoodsType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.GoodsTypeService;
import com.service.impl.GoodsTypeServiceImpl;
import com.utils.Constants;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet("/admin/goodsType")
public class AdminGoodsTypeController extends BaseController {
    private static GoodsTypeService typeService = new GoodsTypeServiceImpl();
    //查看商品分类
    public String showGoodsType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<GoodsType> goodsTypeList = typeService.getAllGoodsType();
        request.setAttribute("goodsTypeList",goodsTypeList);
        return Constants.FORWARD+"/admin/showGoodsType.jsp";
    }
    //删除商品类别
    public String deleteType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        typeService.deleteType(Integer.valueOf(id));
        return null;
    }
    //修改商品类别
    public String updateType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        Integer level = Integer.valueOf(request.getParameter("level"));
        Integer parent = Integer.valueOf(request.getParameter("parent"));
        GoodsType goodsType = new GoodsType(id,name,level,parent);
        typeService.updateType(goodsType);
        return Constants.REDIRECT+"/admin/goodsType?method=showGoodsType";
    }
    //条件查询
    public String searchType(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("name");
        String level = request.getParameter("level");
        List<GoodsType> goodsTypeList = typeService.searchType(name,level);
        request.setAttribute("goodsTypeList",goodsTypeList);
        return Constants.FORWARD+"/admin/showGoodsType.jsp";
    }
    //获取类别列表
    public String getTypes(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<GoodsType> goodsTypeList = typeService.getAllGoodsType();
        request.setAttribute("goodsTypes",goodsTypeList);
        return Constants.FORWARD+"/admin/addGoodsType.jsp";
    }
    //添加分类
    public String addType(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("name");
        Integer parent = Integer.valueOf(request.getParameter("parent"));
        GoodsType goodsType = null;
        if (parent == 0) {
            goodsType = new GoodsType(name,1,parent);
        }else{
            goodsType = new GoodsType(name,2,parent);
        }
        typeService.addGoodsType(goodsType);
        return Constants.REDIRECT+"/admin/goodsType?method=showGoodsType";
    }
}
