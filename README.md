# 🚀 Spring Boot Middleware Plugin Architecture

## 📌 Overview

This project demonstrates a **pluggable middleware architecture** built using **Spring Boot 3.x**.
It is designed to simulate **enterprise-grade middleware systems** used in banking and payment platforms.

The application showcases:

* Plugin-based processing pipeline
* Clean separation of concerns
* Extensible and scalable design
* Production-ready patterns

---

## 🏗️ Architecture

### 🔄 Request Flow

```
Client → Controller → MiddlewareService → PluginExecutor → Plugins → Response
```

### 🧩 Plugin Execution Pipeline

```
Validation → Enrichment → Routing → Transformation
```

* Each step is implemented as an independent **plugin**
* Plugins are dynamically injected using Spring (`List<MiddlewarePlugin>`)
* Registered at startup using `@PostConstruct`

---

## 🧠 Design Patterns Used

* **Chain of Responsibility** → Sequential plugin execution
* **Strategy Pattern** → Multiple plugin implementations
* **Dependency Injection** → Dynamic plugin discovery
* **Interceptor Pattern** → Request pre/post processing
* **Filter Pattern** → Logging & trace handling
* **AOP (Custom Annotation)** → `@LogType`

---

## ⚙️ Tech Stack

* Java 17
* Spring Boot 3.x
* Spring Security
* Maven
* REST APIs

---

## 📁 Project Structure

```
com.example.demo
│
├── controller        → REST APIs
├── service           → Business logic & plugins
├── model             → DTOs & interfaces
├── configuration     → Security & plugin config
├── interceptor       → Request interceptors
├── filter            → Filters (logging, tracing)
├── annotation        → Custom annotations
├── exception         → Exception handling
```

---

## 🔌 Plugin Architecture

### Interface

```java
public interface MiddlewarePlugin {
    void execute(MessageContext context);
}
```

### Implementations

* ValidationPlugin
* EnrichmentPlugin
* RoutingPlugin

### Dynamic Injection

```java
private final List<MiddlewarePlugin> plugins;
```

### Registration at Startup

```java
@PostConstruct
public void init() {
    for (MiddlewarePlugin plugin : plugins) {
        registry.register(plugin);
    }
}
```

---

## 🔐 Security

* Basic Authentication enabled
* Role-based access:

| Endpoint   | Access        |
| ---------- | ------------- |
| /public/** | Open          |
| /user/**   | USER          |
| /admin/**  | ADMIN         |
| Others     | Authenticated |

---

## 📡 APIs

### 🔹 Get All Products

```
GET /say/products/get
```

### 🔹 Get Product by ID

```
GET /say/products/get/{id}
```

### 🔹 Create Product

```
POST /say/products/post
```

### 🔹 Update Product

```
PUT /say/products/put/{id}
```

### 🔹 Middleware Processing

```
POST /say/message/post
```

---

## 🧪 Sample Request

```json
{
  "payload": "{\"amount\":1000,\"currency\":\"INR\"}",
  "transactionId": "TXN123",
  "status": "INITIATED"
}
```

---

## ▶️ Running the Application

### Using Maven

```bash
mvn spring-boot:run
```

### Using JAR

```bash
java -jar target/springboot-demo.jar
```

---

## 📊 Key Features

* Pluggable middleware architecture
* Dynamic plugin execution
* Clean layered design
* Extensible for microservices
* Logging and trace-ready

---

## 🚀 Future Enhancements

* Kafka integration (event-driven architecture)
* CQRS implementation
* Saga pattern orchestration
* MongoDB integration
* Docker & Kubernetes deployment

---

## 🏦 Use Cases

* Payment processing systems
* Banking middleware platforms
* Message transformation engines
* API gateway extensions

---

## 👨‍💻 Author

**Sandeep Shinde**
Senior Solution Architect
Microservices | Banking Domain | Java | Kafka | Cloud

---

## ⭐ Key Highlight

> This project demonstrates how to design a **scalable, extensible middleware system** using modern Spring Boot practices and enterprise design patterns.
