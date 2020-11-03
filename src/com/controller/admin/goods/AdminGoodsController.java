package com.controller.admin.goods;

import com.controller.BaseController;
import com.entity.Goods;
import com.entity.GoodsType;
import com.entity.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.GoodsService;
import com.service.GoodsTypeService;
import com.service.impl.GoodsServiceImpl;
import com.service.impl.GoodsTypeServiceImpl;
import com.utils.Constants;
import com.utils.UploadUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@MultipartConfig()
@WebServlet("/admin/goods")
public class AdminGoodsController extends BaseController {
    private GoodsService service = new GoodsServiceImpl();
    private static GoodsTypeService typeService = new GoodsTypeServiceImpl();
    public String showAllGoods(HttpServletRequest request, HttpServletResponse response){
        String pageIndex = request.getParameter("pageIndex");
        if (pageIndex == null) {
            pageIndex = "1";
        }
        Page page = new Page(Integer.valueOf(pageIndex));
        List<Goods> goodsList = null;
        goodsList = service.getAllGoods();
        //ObjectMapper objectMapper = new ObjectMapper();
        //String str = objectMapper.writeValueAsString(goodsList);
        if (goodsList != null) {
            request.setAttribute("goodsList",goodsList);
            return Constants.FORWARD+"/admin/showGoods.jsp";
        }else{
            request.setAttribute("goodsList","");
            return Constants.FORWARD+"/admin/showGoods.jsp";
        }
    }
    public String searchGoods(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("name");
        String pubDate = request.getParameter("pubDate");
        List<Goods> goodsList = null;
        goodsList = service.searchGoods(name,pubDate);
        request.setAttribute("goodsList",goodsList);
        return Constants.FORWARD+"/admin/showGoods.jsp";
    }
    //获取添加页类别列表
    public String getAddTypes(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<GoodsType> goodsTypeList = typeService.getAllGoodsType();
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(goodsTypeList);
        //request.setAttribute("goodsTypes",goodsTypeList);
        return s;
    }
    //添加商品
    public String addGoods(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException {
        String name = request.getParameter("name");
        String pubDate = request.getParameter("pubDate");
        String price = request.getParameter("price");
        String star = request.getParameter("star");
        String info = request.getParameter("info");
        String typeId = request.getParameter("typeId");
        System.out.println(name+" "+pubDate+" "+price+" "+star+" "+info+" "+typeId);

        Part files = request.getPart("picture");
        //设置上传文件的格式
        List<String> fileTypes = new ArrayList<>();
        fileTypes.add("jpg");
        fileTypes.add("jpeg");
        fileTypes.add("png");
        fileTypes.add("gif");
        //设置文件上传的路径
        String realPath = request.getServletContext().getRealPath("/image");
        System.out.println("第一次路径"+realPath);
        File file = new File(realPath);
        if(!file.exists()){
            file.mkdirs();
        }
        //获取源文件的名称
        //解决IE问题
        String header = files.getHeader("Content-Disposition");
        String substring = header.substring(header.lastIndexOf("filename=")+10,header.length()-1);
        //在整个包含路径的值立，截取出真正的文件名称
        String submittedFileName = substring.substring(substring.lastIndexOf("\\") + 1);
        System.out.println(submittedFileName);
        String ends = submittedFileName.substring(submittedFileName.lastIndexOf(".") + 1);
        //对上传文件的后缀做判断
        if(!fileTypes.contains(ends)){
            response.getWriter().println(submittedFileName+"不符合类型的规范");
            return null;
        }
        String newPath = UploadUtils.newPath(realPath, submittedFileName);
        String newFileName = UploadUtils.newFileName(submittedFileName);
        System.out.println("文件保存路径"+newPath);
        String[] paths = newPath.split("\\\\");
        String finalPath = "";
        for(int i=6; i<paths.length; i++){
            if (i == paths.length-1){
                finalPath = finalPath + paths[i] +"/"+ newFileName;
            }else {
                finalPath = finalPath + paths[i]+"/";
            }
        }
        files.write(newPath+File.separator+newFileName);
        files.delete();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(pubDate);
        Goods goods = new Goods(name,finalPath,date,Integer.valueOf(price),info,Integer.valueOf(star),Integer.valueOf(typeId));
        service.addGoods(goods);
        return Constants.REDIRECT+"/admin/goods?method=showAllGoods";
    }
    //删除商品
    public String deleteGoods(HttpServletRequest request, HttpServletResponse response){
        Integer id = Integer.valueOf(request.getParameter("id"));
        service.deleteGoods(id);
        return Constants.REDIRECT+"/admin/goods?method=showAllGoods";
    }
    //修改商品
    public String updateGoods(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        String pubDate = request.getParameter("pubDate");
        Integer price = Integer.valueOf(request.getParameter("price"));
        Integer star = Integer.valueOf(request.getParameter("star"));
        String info = request.getParameter("info");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(pubDate);
        Goods goods = new Goods(id,name,date,"",price,star,info,1);
        service.updateGoods(goods);
        return Constants.REDIRECT+"/admin/goods?method=showAllGoods";
    }
}
