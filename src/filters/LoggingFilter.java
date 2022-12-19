package filters;

import org.apache.logging.log4j.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;


public class LoggingFilter implements Filter {
    private final Logger logger = LogManager.getLogger("blogApi");

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long startTime = System.currentTimeMillis();
        chain.doFilter(request, response);
        long endTime = System.currentTimeMillis();
        log(request, response, endTime - startTime);
    }

    private void log(ServletRequest req, ServletResponse resp, long execTime) {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        int status = response.getStatus();

        String token = request.getHeader("Authentication");
        String method = request.getMethod();

        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            pathInfo = "";
        }
        String path = request.getServletPath() + pathInfo;

        logger.info("Token: {}, Method: {}, Path: {}, Status: {}, Execution Time: {} ms", token, method, path, status, execTime);
    }

    @Override
    public void destroy() {

    }
}
