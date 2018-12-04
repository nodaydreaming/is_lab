package cn.hznu.islab.util;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class UEditorFilter extends StrutsPrepareAndExecuteFilter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;

        String URL = request.getRequestURI();

        if(URL.endsWith("controller.jsp")){
            System.out.println(URL);
            chain.doFilter(req, res);
        }
        else {
            System.out.println(URL);
            super.doFilter(req, res, chain);
        }
    }
}
