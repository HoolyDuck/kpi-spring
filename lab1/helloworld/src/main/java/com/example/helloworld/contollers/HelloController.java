package com.example.helloworld.contollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("/")
    @ResponseBody
    public String helloWorld() {
        return "Hello, World!";
    }

    @GetMapping("main")
    public String getMainPage(){
        return "main";
    }

    @GetMapping("maxim")
    public String getMaximPage(){
        return "maxim";
    }

    @GetMapping("nazariy")
    public String getNazariyPage(){
        return "nazariy";
    }
    @GetMapping("danylo")
    public String getDanyloPage(){
        return "danylo";
    }

}
