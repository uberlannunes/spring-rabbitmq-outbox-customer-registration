package dev.uberlan.customerregistration.customer;

import dev.uberlan.customerregistration.notification.Notification;
import dev.uberlan.customerregistration.notification.NotificationService;
import dev.uberlan.customerregistration.notification.NotificationStatus;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final NotificationService notificationService;

    public CustomerService(CustomerRepository customerRepository, NotificationService notificationService) {
        this.customerRepository = customerRepository;
        this.notificationService = notificationService;
    }

    @Transactional
    public Customer save(CustomerRequestDTO dto) {
        Customer customer = customerRepository.save(new Customer(dto.name(), dto.email(), dto.address()));
        notificationService.save(new Notification(customer, NotificationStatus.PENDING));
        return customer;
    }
}
