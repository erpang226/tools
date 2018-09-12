package com.syc.demo1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    private Myservice myservice;

    @RequestMapping("/test")
    public String test(){

        String result=myservice.test(1);
        return result;
    }
}
