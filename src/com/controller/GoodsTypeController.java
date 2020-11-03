package com.controller;

import com.entity.GoodsType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.GoodsTypeService;
import com.service.impl.GoodsTypeServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/goodsType")
public class GoodsTypeController extends BaseController {
    private static GoodsTypeService service = new GoodsTypeServiceImpl();
    public String goodsTypeAll(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        List<GoodsType> list = null;
        list = service.getAllGoodsType();
        ObjectMapper objectMapper = new ObjectMapper();
        String str = objectMapper.writeValueAsString(list);
        if (list != null) {
            return str;
        }else{
            return "";
        }
    }
}
