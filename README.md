# Ecommerce Backend API

A secure Ecommerce Backend REST API developed using Spring Boot.

---

## 🚀 Features

✅ User Registration & Login  
✅ JWT Authentication  
✅ BCrypt Password Encryption  
✅ Role-Based Authorization (ADMIN / USER)  
✅ Product Management APIs  
✅ Cart APIs  
✅ Order APIs  
✅ Swagger UI Documentation  
✅ Global Exception Handling  
✅ Validation Handling  
✅ MySQL Database Integration  

---

## 🛠 Tech Stack

- Java 17
- Spring Boot
- Spring Security
- JWT
- MySQL
- JPA / Hibernate
- Swagger OpenAPI
- Maven

---

## 🔐 Authentication

JWT Token based authentication is implemented.

After login, use the generated token in Swagger Authorize section.

---

## 📚 Swagger URL

http://localhost:8080/swagger-ui/index.html

---

## 📦 API Modules

### User APIs
- Register User
- Login User
- Get All Users
- Update User
- Delete User

### Product APIs
- Add Product
- Update Product
- Delete Product
- Get All Products
- Get Product By ID

### Cart APIs
- Add To Cart
- Get User Cart
- Delete Cart Item

### Order APIs
- Place Order
- Get User Orders
- Order Status Handling

---

## 🗄 Database

MySQL Database used with JPA Hibernate.

---

## ▶️ Run Project

### Configure MySQL in application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
```

