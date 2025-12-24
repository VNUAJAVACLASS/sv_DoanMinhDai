package com.vnua.fita.exception;

import com.vnua.fita.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.access.AccessDeniedException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(BadCredentialsException.class)
    public ApiResponse<Object> handleBadCredentials(BadCredentialsException ex) {
        return ApiResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.UNAUTHORIZED.value())
                .message("Tên đăng nhập hoặc mật khẩu không chính xác")
                .data(null)
                .build();
    }

    @ExceptionHandler(AuthenticationException.class)
    public ApiResponse<Object> handleAuthenticationException(AuthenticationException ex) {
        return ApiResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.UNAUTHORIZED.value())
                .message("Xác thực thất bại: " + ex.getMessage())
                .data(null)
                .build();
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ApiResponse<Object> handleAccessDenied(AccessDeniedException ex) {
        return ApiResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.FORBIDDEN.value())
                .message("Bạn không có quyền thực hiện hành động này")
                .data(null)
                .build();
    }

    @ExceptionHandler(RuntimeException.class)
    public ApiResponse<Object> handleRuntimeException(RuntimeException ex) {
        return ApiResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .data(null)
                .build();
    }

    @ExceptionHandler(org.springframework.web.HttpRequestMethodNotSupportedException.class)
    public ApiResponse<Object> handleMethodNotAllowed(Exception ex) {
        return ApiResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.METHOD_NOT_ALLOWED.value())
                .message("Phương thức HTTP không được hỗ trợ cho đường dẫn này")
                .data(null)
                .build();
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class, 
                       MissingServletRequestParameterException.class})
    public ApiResponse<Object> handleTypeMismatch(Exception ex) {
        return ApiResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Tham số truyền vào không hợp lệ hoặc thiếu")
                .data(null)
                .build();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder errors = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            errors.append(errorMessage).append(". ");
        });

        return ApiResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Dữ liệu không hợp lệ: " + errors.toString().trim())
                .data(null)
                .build();
    }
}