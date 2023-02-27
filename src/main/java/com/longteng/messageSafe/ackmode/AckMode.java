package com.longteng.messageSafe.ackmode;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: ConfirmMode
 * @description:    消费端： 消息的可靠性传递--------关闭自动确认
 * @author: lt
 * @date: 2023/2/27
 **/
@RestController
public class AckMode {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequestMapping("/testAckProducter")
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
        rabbitTemplate.convertAndSend("ack_exchange","boot","hello");
        return "ok";
    }
}
