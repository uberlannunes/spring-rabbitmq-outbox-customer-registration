package dev.uberlan.customerregistration.notification;

import dev.uberlan.customerregistration.customer.Customer;
import jakarta.persistence.*;

import java.util.UUID;

@Entity(name = "notifications_outbox")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private NotificationStatus status;

    public Notification() {
    }

    public Notification(Customer customer, NotificationStatus status) {
        this.customer = customer;
        this.status = status;
    }

    public Notification(UUID id, Customer customer, NotificationStatus status) {
        this.id = id;
        this.customer = customer;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", customer=" + customer +
                ", status=" + status +
                '}';
    }
}
