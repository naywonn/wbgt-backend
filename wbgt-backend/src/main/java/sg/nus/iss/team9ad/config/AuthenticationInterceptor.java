package sg.nus.iss.team9ad.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession(true);

        if (session != null && session.getAttribute("authenticatedStaff") != null) {
            // User is logged in, allow access to protected pages
            return true;
        } else {
            // User is not logged in, redirect to login page
            response.sendRedirect("/staff/login?redirect=" + request.getRequestURI());
            return false;
        }
    }
}
