package jzxy.mcdd.backend.exception;

import jakarta.validation.ValidationException;
import jzxy.mcdd.backend.entity.RestBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * GlobalExceptionHandler
 *
 * @version 1.0.0
 * @author: mcdd
 * @date: 2024/9/5 19:34
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {BusinessException.class})
    public ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
        log.error("Business Error Handled  ===> ", ex);
        ErrorResponseException errorResponseException =
                new ErrorResponseException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()),
                        ex.getCause());
        return handleExceptionInternal(
                errorResponseException,
                errorResponseException.getBody(),
                errorResponseException.getHeaders(),
                errorResponseException.getStatusCode(),
                request);
    }

    @ExceptionHandler(value = {RequestRejectedException.class})
    public ResponseEntity<Object> handleRequestRejectedException(RequestRejectedException ex) {
        throw ex;
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<Object> handleAccessDenied(AccessDeniedException ex) {
        throw ex;
    }



    @ExceptionHandler(value = {Throwable.class})
    public ResponseEntity<Object> handleException(Throwable ex, WebRequest request) {
        log.error("System Error Handled  ===> ", ex);
        ErrorResponseException errorResponseException =
                new ErrorResponseException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "System Error"),
                        ex.getCause());
        return handleExceptionInternal(
                errorResponseException,
                errorResponseException.getBody(),
                errorResponseException.getHeaders(),
                errorResponseException.getStatusCode(),
                request);
    }

    /**
     * 与SpringBoot 保持一致，校验不通过打印警告信息，而不是直接抛出异常
     *
     * @param exception 验证异常
     * @return 校验结果
     */
    @ExceptionHandler(ValidationException.class)
    public RestBean<Void> validateError(ValidationException exception) {
        log.warn("Resolved [{}: {}]", exception.getClass().getName(), exception.getMessage());
        return RestBean.failure(400, "请求参数有误");
    }
}
