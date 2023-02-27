package com.longteng.messageSafe.returnMode;

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
public class ConsumerSafeReturn {

        @RabbitListener(queues = "return_queue")
        public void ListenerQueue(Message message){
            //System.out.println(message);
            System.out.println("消费了一条消息"+new String(message.getBody()));
        }

}
