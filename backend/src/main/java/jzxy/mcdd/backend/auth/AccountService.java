package jzxy.mcdd.backend.auth;

import com.baomidou.mybatisplus.extension.service.IService;
import jzxy.mcdd.backend.entity.Account;
import jzxy.mcdd.backend.entity.request.ConfirmResetVO;
import jzxy.mcdd.backend.entity.request.EmailRegisterVO;
import jzxy.mcdd.backend.entity.request.EmailResetVO;
import jzxy.mcdd.backend.entity.request.ModifyEmailVO;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * AccountService
 *
 * @version 1.0.0
 * @author: mcdd
 * @date: 2024/8/11 23:28
 */
public interface AccountService extends IService<Account>, UserDetailsService {
    /**
     * 通过用户名或邮箱查找账户
     *
     * @param text 查找关键字，可以是用户名或邮箱
     * @return 返回匹配的账户信息，如果没有找到返回 null
     */
    Account findAccountByNameOrEmail(String text);

    /**
     * 注册邮箱验证代码
     *
     * @param type    验证类型，例如注册、重置密码等
     * @param email   需要验证的邮箱
     * @param address 验证码发送地址
     * @return 返回生成的验证码或错误提示
     */
    String registerEmailVerifyCode(String type, String email, String address);

    String registerEmailAccount(EmailRegisterVO info);


    /**
     * 通过邮箱重置账户密码
     *
     * @param info 包含邮箱验证信息和新密码的数据对象
     * @return 返回操作结果，成功或失败的原因
     */
    String resetEmailAccountPassword(EmailResetVO info);

    /**
     * 邮箱重置密码后的确认操作
     *
     * @param info 包含新密码和验证码的数据对象
     * @return 返回确认操作的结果，成功或失败的原因
     */
    String resetConfirm(ConfirmResetVO info);

    /**
     * 更改账户密码
     *
     * @param id      用户 ID
     * @param oldPass 原密码
     * @param newPass 新密码
     * @return 返回密码更改结果，成功或失败的原因
     */
    boolean changePassword(int id, String oldPass, String newPass);

    /**
     * 修改账户的邮箱
     *
     * @param id 用户 ID
     * @param vo 包含新邮箱和验证信息的数据对象
     * @return 返回邮箱修改结果，成功或失败的原因
     */
    String modifyEmail(int id, ModifyEmailVO vo);
}
