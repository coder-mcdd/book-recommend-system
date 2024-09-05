package jzxy.mcdd.backend.controller;

import jzxy.mcdd.backend.service.PythonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * PythonController
 *
 * @version 1.0.0
 * @author: mcdd
 * @date: 2024/8/30 23:12
 */
@RestController
@RequestMapping("/api/pythons")
@RequiredArgsConstructor
@Slf4j
public class PythonController {
    private final PythonService service;

    @GetMapping("/hello")
    public String hello() {
        return service.getHelloMessage();
    }

    @GetMapping("/greet")
    public String greet(@RequestParam String name) {
        return service.getGreetingMessage(name);
    }

    @GetMapping("/item")
    public String item(@RequestParam Integer id) {
        return service.getItem(id);
    }
}
