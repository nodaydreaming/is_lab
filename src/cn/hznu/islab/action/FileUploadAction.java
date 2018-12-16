package cn.hznu.islab.action;

import cn.hznu.islab.util.MapToJSON;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
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

    public String uploadTeacherImg() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        HashMap<String, Object> map = new HashMap<>();
        //获得服务器文件存储的文件夹
        String filePath = ServletActionContext.getServletContext().getRealPath("/upload/teacher");
        File fileFolder = new File(filePath);
        //判断文件夹是否存在，若不存在则创建文件夹
        if(!fileFolder.exists()){
            fileFolder.mkdirs();
        }
        //将临时文件移动的目的文件夹
        FileUtils.moveFile(upload, new File(fileFolder, uploadFileName));

        map.put("code", "0");
        map.put("src", filePath + "/" + uploadFileName);

        MapToJSON.mapToJson(response, map);
        return NONE;
    }
}
