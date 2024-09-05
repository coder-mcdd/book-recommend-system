package jzxy.mcdd.backend.exception;

import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import jzxy.mcdd.backend.entity.RestBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ValidationController
 *
 * @version 1.0.0
 * @author: mcdd
 * @date: 2024/8/11 23:22
 */
@Slf4j
@RestControllerAdvice
public class ValidationController {

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
