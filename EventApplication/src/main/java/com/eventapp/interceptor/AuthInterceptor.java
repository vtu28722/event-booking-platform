package com.eventapp.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: Please login first");
            return false;
        }

        // Optional: Admin check for certain paths
        String path = request.getRequestURI();
        String method = request.getMethod();
        if (path.startsWith("/api/events") && !method.equalsIgnoreCase("GET")) {
            String role = (String) session.getAttribute("userRole");
            if (!"ROLE_ADMIN".equals(role)) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("Forbidden: Admin access required");
                return false;
            }
        }

        return true;
    }
}
