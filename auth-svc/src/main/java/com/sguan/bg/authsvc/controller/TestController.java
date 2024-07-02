package com.sguan.bg.authsvc.controller;

import com.sguan.bg.authsvc.annotations.ResultWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ResultWrapper
@RequestMapping("/test")
@RestController
public class TestController {

    @GetMapping("/test1")
    public String test1() {
        System.out.println("hehe");
        return "adsf";
    }

}
