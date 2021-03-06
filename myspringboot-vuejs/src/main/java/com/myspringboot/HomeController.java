package com.myspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
@SpringBootApplication
public class HomeController {

    @RequestMapping("/")
    @ResponseBody
    public String hello() {
        return "Hello World!";
    }
    
    @RequestMapping("/home")
    public String home() {
        return "index";
    }


    public static void main(String[] args) throws Exception {
        SpringApplication.run(HomeController.class, args);
    }
}
