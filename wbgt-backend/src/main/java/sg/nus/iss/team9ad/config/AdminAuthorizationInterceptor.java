package sg.nus.iss.team9ad.config;

import org.springframework.web.servlet.HandlerInterceptor;
import sg.nus.iss.team9ad.model.Staff;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminAuthorizationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession(false);
        Staff authenticatedStaff = new Staff();

        if (session != null && session.getAttribute("authenticatedStaff") != null) {
            // Check if the authenticated staff has the "admin" title
            authenticatedStaff = (Staff) session.getAttribute("authenticatedStaff");
            if ("admin".equals(authenticatedStaff.getTitle())) {
                // User is logged in as admin, allow access to staff operations
                return true;
            }
        }

        // User is not logged in or not authorized, redirect to login page or show an error
        response.sendRedirect("/login"); // or return a custom error response
        return false;
    }
}