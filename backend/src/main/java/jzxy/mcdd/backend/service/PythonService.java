package jzxy.mcdd.backend.service;

/**
 * PythonService
 *
 * @version 1.0.0
 * @author: mcdd
 * @date: 2024/8/30 23:06
 */
public interface PythonService {
    String getHelloMessage();
    String getGreetingMessage(String name);
    String getItem(Integer itemId);
}
