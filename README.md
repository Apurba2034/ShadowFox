# 🏦 Online Banking Management System – Backend

A secure and scalable RESTful API built using **Spring Boot** for managing users, accounts, transactions, and balance transfers.

---

## 🚀 Live API Base URL
https://bankingmanagementsystem-rest-api-backend-production.up.railway.app


---

## 🛠️ Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL (Railway Cloud DB)
- Docker
- Railway (Deployment)

---

## 📌 Features

- User Registration & Login
- Account Creation
- Balance Deposit
- Money Transfer
- Transaction History
- Secure REST APIs
- Cloud Database Integration
- Dockerized Deployment

---

## 📂 API Endpoints

### 🔐 User APIs
| Method | Endpoint | Description |
|--------|----------|------------|
| POST | `/api/user/register` | Register a new user |
| POST | `/api/user/login` | Login user |

### 💳 Account APIs
| Method | Endpoint | Description |
|--------|----------|------------|
| POST | `/api/accounts/create/{userId}` | Create account |
| GET | `/api/accounts/user/{userId}` | Get user account |
| POST | `/api/accounts/deposit` | Deposit money |
| POST | `/api/accounts/transfer` | Transfer money |

---

## 🧪 Sample Request (Login)

```json
{
  "email": "user@gmail.com",
  "password": "1234"
}
```
----
## 🐳 Docker Support

This project is fully Dockerized and production-ready.
