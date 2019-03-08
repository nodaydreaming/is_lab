package cn.hznu.islab.util;

import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class GetCultureImagesUtil {

    public List<String> getCultureImages(){
        List<String> imageList = new LinkedList<>();
        String path = ServletActionContext.getServletContext().getRealPath("/upload/cultureImage");
        String basePath = "/is_lab/upload/cultureImage";

        File file = new File(path);
        if(file.isDirectory()){
            File[] floders = file.listFiles();
            for(int i = 0; i< floders.length; ++i){
                String newPath = basePath + "/" + floders[i].getName();
                File[] files = floders[i].listFiles();
                for(int j = 0; j < files.length; ++j){
                    if(!files[j].isDirectory()){
                        imageList.add(newPath + "/" + files[j].getName());
                    }
                }
            }
        }

        return imageList;
    }

    public static void getFiles(String path){
        File file = new File(path);
        // 如果这个路径是文件夹
        if (file.isDirectory()) {
        // 获取路径下的所有文件
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
            // 如果还是文件夹 递归获取里面的文件 文件夹
                if (files[i].isDirectory()) {
                    System.out.println("目录：" + files[i].getAbsolutePath());
                    getFiles(files[i].getPath());
                }
                else {
                    System.out.println("文件：" + files[i].getName());
                }
            }
        }
        else {
            System.out.println("文件：" + file.getName());
        }
    }


    public static void main(String[] args){
        new GetCultureImagesUtil().getCultureImages();
    }
}
