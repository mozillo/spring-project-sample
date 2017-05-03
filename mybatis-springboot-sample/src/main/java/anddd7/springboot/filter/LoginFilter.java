package anddd7.springboot.filter;

import anddd7.springboot.common.GlobalParm;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class LoginFilter implements Filter {
    private static final Logger logger = Logger.getLogger(LoginFilter.class);

    private static List<String> excludePath = Arrays.asList("login", "register", "oraclejet");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        //包装
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        //获取访问地址
        String url = request.getRequestURL().toString();

        logger.debug(
                String.format("进入拦截器 ,访问 %s 检查用户登录状态",
                        url)
        );

        //访问的不在排出路径内
        if (excludePath.stream().noneMatch(p -> url.contains(p))) {
            //获取登录用户
            HttpSession session = request.getSession(true);
            Object user = session.getAttribute(GlobalParm.USER_SESSION_KEY);

            //判断是否登陆失效
            if (user == null) {
                //识别响应头
                String requestType = request.getHeader("X-Requested-With");
                //如果是ajax类型，响应logout给前台
                if (requestType != null && requestType.equals("XMLHttpRequest"))
                    response.getWriter().print("logout");
                    //如果是访问页面 直接返回首页
                else
                    response.sendRedirect(request.getContextPath() + "/z_login.html");

                return;
            }
        }

        //继续
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {

    }
}


