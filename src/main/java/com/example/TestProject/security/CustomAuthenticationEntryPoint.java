package com.example.TestProject.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        int statusCode = HttpServletResponse.SC_FORBIDDEN; // Mã lỗi mặc định là 403 (truy cập bị từ chối)
        String errorMessage = "Access denied";

        if (authException instanceof InsufficientAuthenticationException) {
            statusCode = HttpServletResponse.SC_UNAUTHORIZED; // Nếu chưa xác thực (401), đặt mã lỗi thành 401
            errorMessage = "You need Login";
        }

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(statusCode);

        // Tạo thông báo lỗi JSON với mã lỗi và thông điệp tương ứng
        Map<String, Object> error = new HashMap<>();
        error.put("error_message", errorMessage);
        error.put("status_code", statusCode);
        error.put("description", "You do not have sufficient permissions to access this resource.");
        error.put("path", request.getRequestURI());

        // Sử dụng ObjectMapper để chuyển đổi Map thành chuỗi JSON và ghi vào OutputStream
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getOutputStream(), error);

    }
}
