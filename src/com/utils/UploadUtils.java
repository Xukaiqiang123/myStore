package com.utils;

import java.io.File;
import java.util.UUID;

public class UploadUtils {

    public static String newFileName(String filename){
        String s = UUID.randomUUID().toString().replaceAll("-", "");
        return s+"_"+filename;
    }

    public static String newPath(String path,String filename){
        //1.通过源文件名称获取hashCode
        int i = filename.hashCode();
        //2.生成2级目录
        int path2 = i&15;
        //3.生成3级目录
        int path3 = i >> 4 & 15;
        String newPath = path + File.separator+ path2+File.separator + path3;
        File file = new File(newPath);
        if(!file.exists()){
            file.mkdirs();
        }

        return newPath;
    }
}
