package com.longteng.messageSafe.confirmMode;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: ConfirmMode
 * @description:    生产端： 消息的可靠性传递--------确认模式
 * @author: lt
 * @date: 2023/2/27
 **/
@RestController
public class ConfirmMode {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequestMapping("/testConfirmProducter")
    public String testProducter(){

        rabbitTemplate.setConfirmCallback((correlationData, booleanResult, s) -> {
            if(booleanResult){
                //发送成功
                System.out.println("发送成功");
            }else {
                //发送失败
                System.out.println("发送失败");
            }
        });
        rabbitTemplate.convertAndSend("confirm_exchange","boot","hello");
        return "ok";
    }
}
