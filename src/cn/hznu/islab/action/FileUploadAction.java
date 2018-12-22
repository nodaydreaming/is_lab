package cn.hznu.islab.action;

import cn.hznu.islab.util.MapToJSON;
import cn.hznu.islab.util.RandomUtil;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class FileUploadAction extends ActionSupport {
    private File upload;
    private String uploadFileName;
    private String uploadContentType;

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public String uploadTeacherImg() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, Object> map = new HashMap<>();
        String filename = RandomUtil.getRandomFileName();
        //获得服务器文件存储的文件夹
        String filePath = ServletActionContext.getServletContext().getRealPath("/upload/teacher");
        File fileFolder = new File(filePath);
        //判断文件夹是否存在，若不存在则创建文件夹
        if(!fileFolder.exists()){
            fileFolder.mkdirs();
        }
        filename += uploadFileName;
        try {
            //将临时文件移动的目的文件夹
            FileUtils.moveFile(upload, new File(fileFolder, filename));
            map.put("code", 0);
            map.put("src", "/is_lab/upload/teacher/" + filename);
        }catch (IOException e){
            map.put("message", "图片上传失败！");
        }

        MapToJSON.mapToJson(response, map);
        return NONE;
    }

    public String uploadStudentImg() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, Object> map = new HashMap<>();
        String filename = RandomUtil.getRandomFileName();
        //获得服务器文件存储的文件夹
        String filePath = ServletActionContext.getServletContext().getRealPath("/upload/student");
        File fileFolder = new File(filePath);
        //判断文件夹是否存在，若不存在则创建文件夹
        if(!fileFolder.exists()){
            fileFolder.mkdirs();
        }
        filename += uploadFileName;
        try {
            //将临时文件移动的目的文件夹
            FileUtils.moveFile(upload, new File(fileFolder, filename));
            map.put("code", 0);
            map.put("src", "/is_lab/upload/student/" + filename);
        }catch (IOException e){
            map.put("message", "图片上传失败！");
        }

        MapToJSON.mapToJson(response, map);
        return NONE;
    }

    public String uploadWorksImg() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, Object> map = new HashMap<>();
        String filename = RandomUtil.getRandomFileName();
        //获得服务器文件存储的文件夹
        String filePath = ServletActionContext.getServletContext().getRealPath("/upload/works");
        File fileFolder = new File(filePath);
        //判断文件夹是否存在，若不存在则创建文件夹
        if(!fileFolder.exists()){
            fileFolder.mkdirs();
        }
        filename += uploadFileName;
        try {
            //将临时文件移动的目的文件夹
            FileUtils.moveFile(upload, new File(fileFolder, filename));
            map.put("code", 0);
            map.put("src", "/is_lab/upload/works/" + filename);
        }catch (IOException e){
            map.put("message", "图片上传失败！");
        }

        MapToJSON.mapToJson(response, map);
        return NONE;
    }

    public String uploadUserImg() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, Object> map = new HashMap<>();
        String filename = RandomUtil.getRandomFileName();
        //获得服务器文件存储的文件夹
        String filePath = ServletActionContext.getServletContext().getRealPath("/upload/admin");
        File fileFolder = new File(filePath);
        //判断文件夹是否存在，若不存在则创建文件夹
        if(!fileFolder.exists()){
            fileFolder.mkdirs();
        }
        filename += uploadFileName;
        try {
            //将临时文件移动的目的文件夹
            FileUtils.moveFile(upload, new File(fileFolder, filename));
            map.put("code", 0);
            map.put("src", "/is_lab/upload/admin/" + filename);
        }catch (IOException e){
            map.put("message", "图片上传失败！");
        }

        MapToJSON.mapToJson(response, map);
        return NONE;
    }

    public String uploadPaper() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, Object> map = new HashMap<>();
        //获得服务器文件存储的文件夹
        String filePath = ServletActionContext.getServletContext().getRealPath("/upload/paper");
        File fileFolder = new File(filePath);
        //判断文件夹是否存在，若不存在则创建文件夹
        if(!fileFolder.exists()){
            fileFolder.mkdirs();
        }
        String filename = RandomUtil.getRandomFileName();
        filename += uploadFileName;
        try {
            //将临时文件移动的目的文件夹
            FileUtils.moveFile(upload, new File(fileFolder, filename));
            map.put("code", 0);
            map.put("src", "/is_lab/upload/paper/" + filename);
        }catch (IOException e){
            map.put("message", "文件上传失败！");
        }

        MapToJSON.mapToJson(response, map);
        return NONE;
    }

    public String uploadSoft() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, Object> map = new HashMap<>();
        String filename = RandomUtil.getRandomFileName();
        //获得服务器文件存储的文件夹
        String filePath = ServletActionContext.getServletContext().getRealPath("/upload/soft");
        File fileFolder = new File(filePath);
        //判断文件夹是否存在，若不存在则创建文件夹
        if(!fileFolder.exists()){
            fileFolder.mkdirs();
        }
        filename += uploadFileName;
        try {
            //将临时文件移动的目的文件夹
            FileUtils.moveFile(upload, new File(fileFolder, filename));
            map.put("code", 0);
            map.put("src", "/is_lab/upload/soft/" + filename);
        }catch (IOException e){
            map.put("message", "文件上传失败！");
        }

        MapToJSON.mapToJson(response, map);
        return NONE;
    }
}
