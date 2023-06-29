package dev.uberlan.customernotification.rabbitmq.consumer;

import dev.uberlan.rabbitmq.dto.NotificationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CustomerNotificationConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerNotificationConsumer.class);

    @RabbitListener(queues = "${queue.customer-notification.name}")
    public void customerNotificationConsumer(NotificationDTO notificationDTO) {
        LOGGER.info("sending notification to customerId={}, customerName={}, email={}, message={}",
                notificationDTO.customerId(),
                notificationDTO.customerName(),
                notificationDTO.email(),
                notificationDTO.message());
    }
}
