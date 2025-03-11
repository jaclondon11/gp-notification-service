# Notification Service

## Overview
The Notification Service is a crucial component of the Gaming Platform, responsible for handling and managing all notification-related operations. It processes events from other services and ensures users receive timely notifications about various gaming platform activities.

## Features
- Real-time notification processing
- Kafka event consumption
- Support for multiple notification types:
  - Game invites
  - Friend requests
  - Achievement notifications
  - System announcements

## Tech Stack
- Java/Spring Boot
- Apache Kafka
- Spring Kafka
- Spring Web
- Spring Actuator

## Prerequisites
- Java 17 or higher
- Maven
- Kafka (provided via docker-compose)

## Local Development Setup

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd gp-notification-service
   ```

2. **Build the application**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

The service will start on port 8083 by default.

## API Endpoints

### Health Check
- `GET /actuator/health` - Check service health status

### Notification Endpoints
(Document your specific endpoints here)

## Kafka Topics

The service listens to the following Kafka topics:
- `game-notifications` - Game-related notifications
- `social-notifications` - Social interaction notifications


## Configuration

Key configuration properties (application.properties/yaml):
```yaml
server:
  port: 8083

spring:
  kafka:
    bootstrap-servers: kafka:9092
    consumer:
      group-id: notification-service
```

## Testing
Run the tests using:
```bash
mvn test
```
