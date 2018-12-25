package cn.hznu.islab.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class FileDownloadAction extends ActionSupport {
    private String filename;
    private InputStream inputStream;
    private String address;

    public void setAddress(String address) {
        this.address = address.split(",")[0];
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) throws UnsupportedEncodingException {
        this.filename = new String(filename.getBytes("UTF-8"), StandardCharsets.ISO_8859_1);
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String downFile(){
        inputStream = ServletActionContext.getServletContext().getResourceAsStream(address);
        return SUCCESS;
    }


}
