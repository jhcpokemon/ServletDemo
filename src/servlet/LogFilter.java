package servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Date;

@WebFilter(filterName = "LogFilter")
public class LogFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {
        String initParam = config.getInitParameter("test-param");
        System.out.println("Test param: " + initParam);
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String ip = req.getRemoteAddr();
        System.out.println("IP: " + ip + ",time: " + new Date().toString());
        chain.doFilter(req, resp);
    }

    public void destroy() {
    }
}
