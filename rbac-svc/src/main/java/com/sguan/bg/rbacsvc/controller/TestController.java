package com.sguan.bg.authsvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class TestController {

    @GetMapping("/test1")
    public String test1() {
        System.out.println("hehe");
        return "adsf";
    }

}
