package com.longteng.messageSafe.returnMode;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: ConfirmMode
 * @description:     MQ端 消息的可靠性传递--------返回模式
 * @author: lt
 * @date: 2023/2/27
 **/
@RestController
public class ReturnMode {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequestMapping("/testReturnProducter")
    public String testProducter(){
        //只有在路由出错的时候才会执行
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int i, String s, String s1, String s2) {
                System.out.println("执行了return的回调函数");
                System.out.println(message);
                System.out.println(i);
                System.out.println(s);
                System.out.println(s1);
                System.out.println(s2);
            }
        });
        rabbitTemplate.convertAndSend("return_exchange","boot2","hello");
        return "ok";
    }
}
