package com.zdz.test.web.controller;

import com.zdz.test.web.service.HelloService;
import com.zdz.test.web.tool.mail.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    /**
     * spring boot+JPA
     */
    @Autowired
    HelloService helloService;
    @RequestMapping("/hello")
    public String hello(){

        Mail mail=new Mail();
        mail.send("584638114@qq.com","cs","123AbC测试");
        helloService.sayHi();
        return "hello------";
    }

}
