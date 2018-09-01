package com.bittech.javaweb.web.servlet;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Author: secondriver
 * Created: 2018/7/29
 */
public class ApplicationLoadServlet extends HttpServlet {
    
    protected static final String CURRENT_USER = "currentUser";
    
    private static ApplicationContext context = null;
    
    @Override
    public void init() throws ServletException {
        super.init();
        if (context == null) {
            context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        }
    }
    
    /**
     * 根据名称获取Bean
     *
     * @param name
     * @return
     */
    public static Object getBean(String name) {
        return context.getBean(name);
    }
    
    /**
     * 根据类型获取Bean
     *
     * @param requiredType
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> requiredType) {
        return context.getBean(requiredType);
    }
    
    public static void write(HttpServletResponse response, String content) throws Exception {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write(content);
        out.flush();
        out.close();
    }
    
    @Override
    public void destroy() {
        super.destroy();
        context = null;
    }
}
