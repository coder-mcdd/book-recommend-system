package jzxy.mcdd.backend.mail;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * MailQueueListener
 *
 * @version 1.0.0
 * @author: mcdd
 * @date: 2024/8/12 11:19
 */
@Component
@RabbitListener(queues = "mail")
@RequiredArgsConstructor
@Import(MailSenderAutoConfiguration.class) // fix IDEA bug
public class MailQueueListener {

    private final JavaMailSender sender;

    @Value("${spring.mail.username}")
    private String username;

    /**
     * 处理邮件发送
     *
     * @param data 邮件信息
     */
    @RabbitHandler
    public void sendMailMessage(Map<String, Object> data) {
        String email = data.get("email").toString();
        Integer code = (Integer) data.get("code");
        SimpleMailMessage message = switch (data.get("type").toString()) {
            case "register" -> createMessage("欢迎注册我们的网站",
                    "您的邮件注册验证码为: " + code + "，有效时间 3 分钟，为了保障您的账户安全，请勿向他人泄露验证码信息",
                    email);
            case "reset" -> createMessage("您的密码重置邮件",
                    "你好，您正在执行重置密码操作，验证码: " + code + "，有效时间 3 分钟，如非本人操作，请无视",
                    email);
            case "modify" -> createMessage("您的邮件修改验证邮件",
                    "您好，您正在绑定新的电子邮件地址，验证码: " + code + "，有效时间 3 分钟，如非本人操作，请无视",
                    email);
            default -> null;
        };
        if (message == null) return;
        sender.send(message);
    }

    /**
     * 快速封装简单邮件消息实体
     *
     * @param title   标题
     * @param content 内容
     * @param email   收件人
     * @return 邮件实体
     */
    private SimpleMailMessage createMessage(String title, String content, String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(title);
        message.setText(content);
        message.setTo(email);
        message.setFrom(username);
        return message;
    }
}
