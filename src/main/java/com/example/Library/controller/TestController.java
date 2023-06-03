package com.example.Library.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1")
public class TestController {

    //Api Test
    @GetMapping("/test")
    public String test() {
        return ("test successful");
    }
}
