package cn.hznu.islab.util;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ClassName SessionFilter
 * @Description
 * @Author GYJ
 * @Date 2018/11/18 15:59
 * @Version 1.0
 **/
public class SessionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //如果session不为空就返回该session，如果为空就返回null
        HttpSession session = request.getSession(false);

        //获得根目录所对应的绝对路径
        String currentURL = request.getRequestURI();
//        System.out.println("拦截过滤");
//        System.out.println("用户的访问地址为：" + currentURL);
        //截取到当前文件名用于比较
        String targetURL = currentURL.substring(currentURL.indexOf("/",1));
//        System.out.println(targetURL);
        if(targetURL.contains("admin")){
            if(session == null || session.getAttribute("loginUser") == null){
                response.sendRedirect(request.getContextPath() + "/login.html");
            }
        }
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
