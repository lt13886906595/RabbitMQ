package com.longteng.messageSafe.returnMode;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfigReturn {

    public static final String EXCHANGE_NAME = "return_exchange";
    public static final String QUEUE_NAME = "return_queue";

    //1.交换机
    @Bean("return_exchange")
    public Exchange bootExchange(){
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
    }


    //2.Queue 队列
    @Bean("return_queue")
    public Queue bootQueue(){
        return QueueBuilder.durable(QUEUE_NAME).build();
    }

    //3. 队列和交互机绑定关系 Binding
    /*
        1. 知道哪个队列
        2. 知道哪个交换机
        3. routing key
     */
    @Bean("return_queue_bind")
    public Binding bindQueueExchangeConfirm(@Qualifier("return_queue") Queue queue, @Qualifier("return_exchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("boot.#").noargs();
    }


}
