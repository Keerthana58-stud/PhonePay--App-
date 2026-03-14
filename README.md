# PhonePe Wallet Clone

A full-stack digital wallet application inspired by PhonePe.
Users can create accounts, manage a wallet, send money using UPI IDs, add balance, and view transaction history.

This project demonstrates a real-world payment flow built with **Spring Boot + MongoDB + React**.

---

## Features

* User Signup & Login
* UPI-based money transfer
* Wallet balance management
* Add money to wallet
* Transaction history
* REST API documentation with Swagger
* React dashboard UI
* Backend-frontend integration

---

## Tech Stack

### Backend

* Java
* Spring Boot
* Spring Data MongoDB
* Maven
* Swagger / OpenAPI

### Frontend

* React
* React Router
* Axios
* CSS

### Database

* MongoDB

---

## Project Structure

```
phonepe-wallet-clone
│
├── walletapp-backend
│   ├── src/main/java/com/wallet/walletapp
│   │   ├── controller
│   │   ├── service
│   │   ├── repository
│   │   ├── model
│   │   ├── dto
│   │   └── config
│   └── pom.xml
│
├── wallet-frontend
│   ├── src
│   │   ├── api
│   │   ├── components
│   │   ├── pages
│   │   └── App.js
│   └── package.json
│
└── README.md
```

---

## How It Works

1. User signs up with:

   * name
   * email
   * phone
   * password
   * UPI ID

2. Backend creates:

   * user record
   * wallet with balance = 0

3. User can:

   * add money to wallet
   * send money using UPI ID

4. Payment flow:

   * sender wallet debited
   * receiver wallet credited
   * transaction recorded

---

## Backend Setup

### 1. Install requirements

* Java 17+
* Maven
* MongoDB

### 2. Start MongoDB

Example:

```
mongod
```

Default port:

```
mongodb://localhost:27017
```

### 3. Run backend

```
cd walletapp-backend
mvn spring-boot:run
```

Backend runs on:

```
http://localhost:8080
```

---

## Swagger API Documentation

Open:

```
http://localhost:8080/swagger-ui.html
```

or

```
http://localhost:8080/swagger-ui/index.html
```

---

## Frontend Setup

### Install dependencies

```
cd wallet-frontend
npm install
```

### Start React app

```
npm start
```

Frontend runs on:

```
http://localhost:3000
```

---

## Example API Requests

### Signup

```
POST /auth/signup
```

Body:

```
{
  "name": "Keerthi",
  "email": "keerthi@gmail.com",
  "phone": "9999999999",
  "password": "1234",
  "upiId": "keerthi@phonepe"
}
```

---

### Send Money

```
POST /payment/send
```

Body:

```
{
  "senderUpi": "keerthi@phonepe",
  "receiverUpi": "ravi@phonepe",
  "amount": 500
}
```

---

### Get Transactions

```
GET /transactions/user/{upiId}
```

---

## Future Improvements

* JWT authentication
* QR code payments
* Bank account integration
* Payment gateway simulation
* Real-time notifications
* Mobile responsive UI

---

## Author

Keerthi

B.Tech Engineering Student
Interested in AI/ML and Full Stack Development

---

## License

This project is for educational and portfolio purposes.
