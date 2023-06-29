package dev.uberlan.customerregistration.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerRequestDTO (
        @NotBlank(message = "Name is mandatory")
        String name,
        @Email(message = "Email is invalid")
        String email,
        String address){

    @Override
    public String toString() {
        return "CustomerRequestDTO{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
