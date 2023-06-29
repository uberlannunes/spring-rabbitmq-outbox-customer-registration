# Customer Registration Project

This project is responsible for managing customer registration.
<br><br>
After registration, a scheduled task is triggered every 30 seconds to publish the events of PENDING records to RabbitMQ.
<br><br>
The Project implements the following design patterns:
- Publish–subscribe
- Outbox

## Technology Stack

The project utilizes the following technologies and frameworks:

- Java
- Spring Boot
- RabbitMQ
- PostgreSQL
- Docker (for multi-stage build)
- Maven (for build and dependency management)
