package dev.uberlan.customerregistration.rabbitmq;

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    private final String EXCHANGE_NOTIFICATION_NAME;
    private final String QUEUE_NOTIFICATION_NAME;

    private final AmqpAdmin amqpAdmin;


    public RabbitmqConfig(@Value("${exchange.customer-notification.name}") String exchangeNotificationName,
                          @Value("${queue.customer-notification.name}") String queueNotificationName,
                          AmqpAdmin amqpAdmin) {
        this.EXCHANGE_NOTIFICATION_NAME = exchangeNotificationName;
        this.QUEUE_NOTIFICATION_NAME = queueNotificationName;
        this.amqpAdmin = amqpAdmin;
    }

    private Queue queue(String name) {
        return new Queue(name, true, false, false);
    }

    private DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_NOTIFICATION_NAME);
    }

    private Binding binding(Queue queue, DirectExchange directExchange) {
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, directExchange.getName(), queue.getName(), null);
    }

    @PostConstruct
    private void start() {
        var queueNotification = queue(QUEUE_NOTIFICATION_NAME);
        var directExchange = directExchange();

        var bindingNotification = binding(queueNotification, directExchange);

        amqpAdmin.declareQueue(queueNotification);
        amqpAdmin.declareExchange(directExchange);
        amqpAdmin.declareBinding(bindingNotification);
    }
}
