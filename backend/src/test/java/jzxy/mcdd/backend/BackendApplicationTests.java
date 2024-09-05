package jzxy.mcdd.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
class BackendApplicationTests {

    @Autowired
    ApplicationContext context;

    @Test
    void contextLoads() {
        System.out.println("context.getBean(JavaMailSender.class) = " + context.getBean(JavaMailSender.class));
    }

}
