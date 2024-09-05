package jzxy.mcdd.backend.entity.request;

import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * EmailResetVO
 *
 * @version 1.0.0
 * @author: mcdd
 * @date: 2024/8/11 23:29
 */
@Data
public class EmailResetVO {
    @Email(message = "请输入有效的邮箱地址")
    String email;
    @Length(max = 6, min = 6, message = "验证码长度需为 6")
    String code;
    @Length(min = 6, max = 20,message = "密码长度 [6,20]")
    String password;
}
