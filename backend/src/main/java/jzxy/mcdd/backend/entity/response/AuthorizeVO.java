package jzxy.mcdd.backend.entity.response;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * AuthorizeVO
 *
 * @version 1.0.0
 * @author: mcdd
 * @date: 2024/8/11 23:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizeVO {
    private String username;
    private String email;
    private String avatar;
    private String role;
    private String token;
    private Date expire;
}
