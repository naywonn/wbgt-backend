package sg.nus.iss.team9ad.config;

import org.springframework.http.HttpStatus;
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
        
        if (session != null) {
            if(session.getAttribute("authenticatedStaff") != null) {
                Staff authenticatedStaff = (Staff) session.getAttribute("authenticatedStaff");
            // Check if the authenticated staff has the "admin" title
            if ("admin".equals(authenticatedStaff.getTitle())) {
                // User is logged in as admin, allow access to staff operations
                return true;
            }
                session.invalidate();
            }
        }
        System.out.println("Session is not present trying to redirect");
        // User is not logged in or not authorized, send an unauthorized response
        response.setStatus(HttpStatus.UNAUTHORIZED.value()); // 401 Unauthorized
        response.getWriter().write("Unauthorized: Access Denied"); // Send a message indicating the authorization failure
        return false;
    }
}