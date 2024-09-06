package jzxy.mcdd.backend;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.extra.spring.SpringUtil;
import jzxy.mcdd.backend.context.PythonServerContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Import;
import org.springframework.context.event.EventListener;

import java.net.InetAddress;

@Slf4j
@SpringBootApplication
@Import(SpringUtil.class)
@MapperScan("jzxy.mcdd.backend.mapper")
@RequiredArgsConstructor
public class BackendApplication {

    private final PythonServerContextHolder pythonServerContextHolder;

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @SneakyThrows
    @EventListener(ApplicationReadyEvent.class)
    public void ready() {
//        pythonServerContextHolder.init();

        InetAddress localHost = InetAddress.getLocalHost();
        String address = CharSequenceUtil.format("http://{}:{}", localHost.getHostAddress(), SpringUtil.getProperty("server.port"));
        String runSuccessWarn = "\n====================================================================================\n" +
                "=                        Book-Recommend-System Run Success                         =\n" +
                "=                        address:" + address + "                            =\n" +
                "====================================================================================\n";
        log.info(runSuccessWarn);
    }

}

