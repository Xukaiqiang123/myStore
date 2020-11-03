package com.utils;

import java.io.File;
import java.util.HashMap;

public class DownLoadUtils {
    public static void listFiles(File file, HashMap<String, String> map) {
        File[] files = file.listFiles();//获取当前目录下所有的文件对象
        if (files != null && files.length > 0) {
            for (File f : files) {
                if (f.isDirectory()) {//是不是文件夹
                    listFiles(f, map);
                }else{//是文件
                    String uuidName = f.getName();
                    String filename = uuidName.substring(uuidName.indexOf("_")+1);
                    map.put(uuidName, filename);
                }
            }
        }
    }
}
