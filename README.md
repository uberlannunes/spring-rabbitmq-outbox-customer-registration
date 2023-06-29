# Spring Boot Microservice Customer Registration and Notification System

This is a microservice system built with Spring Boot. It consists of two projects: a Customer Registration Project and a Customer Notification Project.
<br><br>
The System implements the following design patterns:
- Publish–subscribe
- Outbox

### Customer Registration Project

The Customer Registration Project is responsible for managing customer registration.

### Customer Notification Project

The Customer Notification Project is responsible for sending notifications to customers.

## Technology Stack

The project utilizes the following technologies and frameworks:

- Java
- Spring Boot
- RabbitMQ
- PostgreSQL
- Docker
- Maven (for build and dependency management)

### Prerequisites

Before running, make sure you have the following prerequisites installed:
- Docker
- Docker Compose

### Setup

1. Clone the repository:

```bash
git clone https://github.com/uberlannunes/spring-rabbitmq-outbox-customer-registration.git
```

```bash
cd spring-rabbitmq-outbox-customer-registration
```

```bash
docker compose up -d
```

## Usage

To interact with the Registration API, you can make REST calls using the following example as a reference:

```bash
curl --location 'http://localhost:8080/api/customers' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "customer test",
    "email": "customer@test.com",
    "address": "address test"
}'
```

Afterward, you can view the system logs by executing the following command within the project folder:
```bash
docker compose logs -f
```