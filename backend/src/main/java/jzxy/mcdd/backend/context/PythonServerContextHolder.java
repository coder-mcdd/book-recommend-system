package jzxy.mcdd.backend.context;

import cn.hutool.core.util.StrUtil;
import jzxy.mcdd.backend.exception.PythonServerException;
import jzxy.mcdd.backend.utils.Const;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * PythonServerContextHolder
 *
 * @version 1.0.0
 * @author: mcdd
 * @date: 2024/9/6 09:37
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PythonServerContextHolder {
    private final RestTemplate restTemplate;

    private static final String PYTHON_SERVER_HELLO_URL = Const.PYTHON_SERVER_BASE_URL + "hello";

    public void init() {
        log.info("Initializing Python Server Context");
        try {
            String result = restTemplate.getForObject(PYTHON_SERVER_HELLO_URL, String.class);
            if (StrUtil.isNotBlank(result)) {
                retry();
            }
            log.info("Python Server Context: {}", result);
        } catch (Exception e) {
            throw new PythonServerException(e.getMessage());
        }
    }

    private void retry(){

    }
}
