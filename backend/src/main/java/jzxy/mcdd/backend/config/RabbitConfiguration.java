package jzxy.mcdd.backend.config;

import jzxy.mcdd.backend.utils.Const;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitConfiguration
 *
 * @version 1.0.0
 * @author: mcdd
 * @date: 2024/8/11 23:17
 */
@Configuration
public class RabbitConfiguration {
    /**
     * 创建并定义一个名为 mail 的持久化队列。
     *
     * @return Queue 返回构建好的队列对象。
     */
    @Bean("mailQueue")
    public Queue queue(){
        return QueueBuilder
                .durable(Const.MQ_MAIL)
                .build();
    }
}
