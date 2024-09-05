package jzxy.mcdd.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * Account
 *
 * @version 1.0.0
 * @author: mcdd
 * @date: 2024/8/11 23:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account implements BaseData {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @Parameter(name = "username", description = "用户名", in = ParameterIn.PATH, required = true)
    @Pattern(regexp = "^[a-zA-Z0-9\\u4e00-\\u9fa5]+$")
    @Length(min = 1, max = 10, message = "用户名长度需为 1-10")
    private String username;
    @Parameter(name = "password", description = "密码", in = ParameterIn.PATH, required = true)
    @Length(min = 6, max = 20, message = "密码长度需为 6-20")
    private String password;
    @Email(message = "请输入有效的邮箱地址")
    private  String email;
    @Parameter(description = "用户角色")
    private String role;
    @Parameter(description = "用户头像 URL")
    private String avatar;
    @Parameter(description = "注册时间")
    private  Date registerTime;
}
