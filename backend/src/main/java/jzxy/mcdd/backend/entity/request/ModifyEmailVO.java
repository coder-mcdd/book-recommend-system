package jzxy.mcdd.backend.entity.request;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * ModifyEmailVO
 *
 * @version 1.0.0
 * @author: mcdd
 * @date: 2024/8/11 23:30
 */
@Data
public class ModifyEmailVO {
    @Parameter(name = "email", description = "邮箱地址", in = ParameterIn.PATH, required = true)
    @Email(message = "请输入有效的邮箱地址")
    String email;
    @Parameter(name = "code", description = "验证码", in = ParameterIn.PATH, required = true)
    @Length(min = 6, max = 6, message = "验证码长度需为 6")
    String code;
}
