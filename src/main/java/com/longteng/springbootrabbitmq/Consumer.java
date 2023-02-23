package com.longteng.springbootrabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @className: Consumer
 * @description: 类描述
 * @author: lt
 * @date: 2023/2/23
 **/
@Component
public class Consumer {

        @RabbitListener(queues = "boot_queue")
        public void ListenerQueue(Message message){
            //System.out.println(message);
            System.out.println(new String(message.getBody()));
        }

}
