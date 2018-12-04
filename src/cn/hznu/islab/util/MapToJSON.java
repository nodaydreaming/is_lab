package cn.hznu.islab.util;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class MapToJSON {
    /**
     * map 通过Gson转成JSON格式的数据，通过response返回前端
     * @param response
     * @param map
     */
    public static void mapToJson(HttpServletResponse response, Map map) throws IOException {
        response.setHeader("Cache-Control", "no-cache"); //取消浏览器缓存
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();

        Gson gson = new Gson();

        String result = gson.toJson(map);

        writer.print(result);
        writer.flush();
        writer.close();
    }
}
