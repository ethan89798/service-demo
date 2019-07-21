package org.ethan.zk.productdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/testPath")
    public String testPath() {
        return "product demo";
    }
}
