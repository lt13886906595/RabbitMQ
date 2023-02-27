package com.longteng.messageSafe.ackmode;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfigAck{

    public static final String EXCHANGE_NAME = "ack_exchange";
    public static final String QUEUE_NAME = "ack_queue";

    //1.交换机
    @Bean("ack_exchange")
    public Exchange bootExchange(){
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
    }


    //2.Queue 队列
    @Bean("ack_queue")
    public Queue bootQueue(){
        return QueueBuilder.durable(QUEUE_NAME).build();
    }

    //3. 队列和交互机绑定关系 Binding
    /*
        1. 知道哪个队列
        2. 知道哪个交换机
        3. routing key
     */
    @Bean("ack_bindQueue")
    public Binding bindQueueExchangeConfirm(@Qualifier("ack_queue") Queue queue, @Qualifier("ack_exchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("boot.#").noargs();
    }


}
