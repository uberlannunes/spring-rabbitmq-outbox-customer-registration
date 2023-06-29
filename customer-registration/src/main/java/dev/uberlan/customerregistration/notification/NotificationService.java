package dev.uberlan.customerregistration.notification;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> getPendingNotifications() {
        return notificationRepository.findByStatus(NotificationStatus.PENDING);
    }

    @Transactional
    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Transactional
    public void updateStatus(UUID notificationId, NotificationStatus status) {
        Notification notification = notificationRepository.getReferenceById(notificationId);
        notification.setStatus(status);
        notificationRepository.save(notification);
    }
}
