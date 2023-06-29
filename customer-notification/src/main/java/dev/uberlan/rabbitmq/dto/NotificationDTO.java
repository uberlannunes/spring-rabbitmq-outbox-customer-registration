package dev.uberlan.rabbitmq.dto;

import java.io.Serializable;
import java.util.UUID;

public record NotificationDTO(UUID id, UUID customerId, String customerName, String email, String message) implements Serializable {
}