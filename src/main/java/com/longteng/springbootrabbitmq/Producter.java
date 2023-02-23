package com.longteng.springbootrabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @className: Producter
 * @description: 类描述
 * @author: lt
 * @date: 2023/2/23
 **/
@RestController
public class Producter {
    @Autowired
    RabbitTemplate  rabbitTemplate;

    @RequestMapping("/testProducter")
    public String testProducter(){
        rabbitTemplate.convertAndSend("boot_topic_exchange","boot","hello");
        return "ok";
    }
}
