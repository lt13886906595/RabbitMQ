package com.longteng.messageSafe.ackmode;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * @className: Consumer
 * @description: 类描述
 * @author: lt
 * @date: 2023/2/23
 **/
@Component
public class ConsumerAckSafe {

        @RabbitListener(queues = "ack_queue",ackMode = "MANUAL")
        public void ListenerQueue(Message message, Channel channel) throws IOException {
            long deliveryTag = message.getMessageProperties().getDeliveryTag();
            try {
                //1、接受消息
                System.out.println("消费了一条消息"+new String(message.getBody()));
                //2、处理业务
                System.out.println("处理业务");
                //2.1加入发生了异常
                int i = 1/0;
                //3、手动确认 long deliveryTag, boolean multiple
                channel.basicAck(deliveryTag,true);
            } catch (Exception e) {
                System.out.println("发生了异常");
                //发生了异常重新会到队列  long deliveryTag, boolean multiple, boolean requeue
                channel.basicNack(deliveryTag,true,true);
            }
        }

}
