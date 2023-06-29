package dev.uberlan.customerregistration.job;

import dev.uberlan.customerregistration.notification.NotificationService;
import dev.uberlan.customerregistration.notification.NotificationStatus;
import dev.uberlan.customerregistration.rabbitmq.RabbitmqService;
import dev.uberlan.rabbitmq.dto.NotificationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SendNotificationToBrockerJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(SendNotificationToBrockerJob.class);

    private final NotificationService notificationService;
    private final RabbitmqService rabbitmqService;
    private final String QUEUE_NOTIFICATION_NAME;

    public SendNotificationToBrockerJob(NotificationService notificationService,
                                        RabbitmqService rabbitmqService,
                                        @Value("${queue.customer-notification.name}") String QUEUE_NOTIFICATION_NAME) {
        this.notificationService = notificationService;
        this.rabbitmqService = rabbitmqService;
        this.QUEUE_NOTIFICATION_NAME = QUEUE_NOTIFICATION_NAME;
    }

    @Scheduled(fixedDelay = 30000)
    public void run() {
        LOGGER.info("sending notifications to broker...");

        notificationService.getPendingNotifications()
                .stream()
                .map(n -> new NotificationDTO(n.getId(), n.getCustomer().getId(), n.getCustomer().getName(), n.getCustomer().getEmail(), "your registration was successful!"))
                .forEach(n -> {
                    rabbitmqService.sendMessage(QUEUE_NOTIFICATION_NAME, n);
                    notificationService.updateStatus(n.id(), NotificationStatus.DONE);
                });
    }
}
