# 💰 Loan Application & EMI Management System

## 📌 Project Overview

The **Loan Application & EMI Management System** is a Java-based desktop application designed to manage the complete lifecycle of customer loans.

The system allows loan officers to register customers, process loan applications, approve loans, generate EMI schedules, track repayments, detect overdue EMIs, search loan records, and generate reports.

The project is developed using **Java, Object-Oriented Programming (OOP), Java Collections Framework, Exception Handling, and Java Swing**.

As an additional feature, the project also includes **Gold Loan Management**, where gold weight is considered during the loan application process.

---

## 🎯 Objectives

The main objectives of the system are:

- Maintain customer and loan account information.
- Support different types of loans.
- Calculate loan interest and EMI.
- Generate EMI repayment schedules.
- Track repayment history.
- Identify overdue installments.
- Search loan and customer records.
- Sort and organize loan records.
- Provide a graphical user interface for loan officers.
- Validate customer and loan information.
- Generate loan-related reports.

---

# 🏦 Loan Types

The system supports the following loan types:

### 1. Personal Loan
Used for personal financial requirements.

### 2. Home Loan
Used for purchasing or constructing a house.

### 3. Vehicle Loan
Used for purchasing a vehicle.

### 4. Gold Loan ⭐ Extra Feature
An additional feature implemented beyond the basic case study.

Gold Loan uses the customer's **gold weight** as an additional input during loan processing.

---

# 📋 Expected Modules

The project implements the following modules:

## 1. Customer Registration

This module allows the loan officer to register customers.

Customer information includes:

- Customer ID
- Name
- Phone number
- Email
- Income
- Credit score

The system validates important customer information before registration.

---

## 2. Loan Application

Customers can apply for different types of loans.

The application includes:

- Loan ID
- Customer details
- Loan type
- Loan amount
- Loan tenure
- Interest rate

The system validates the loan amount and tenure before creating the loan.

---

## 3. Loan Approval

The system validates customer information before approving a loan.

Validation includes:

- Customer income
- Credit score
- Loan amount
- Required information

After successful validation, the loan can be approved.

---

## 4. EMI Schedule Generation

After a loan is approved, the system generates the EMI schedule.

The EMI calculation uses:

- Principal amount
- Interest rate
- Loan tenure

The system generates individual EMI records containing information such as:

- EMI number
- EMI amount
- Due date
- Payment status

---

## 5. Repayment Tracking

The system maintains the repayment history of customers.

It records:

- Loan ID
- EMI information
- Payment date
- Payment status
- Repayment details

A `LinkedList` is used to maintain repayment history.

---

## 6. Overdue Detection

The system checks whether an EMI has passed its due date without being paid.

If an EMI is unpaid after the due date, it is identified as an overdue EMI.

This helps loan officers identify accounts that require attention.

---

## 7. Loan Search

The system provides loan searching functionality.

Loans can be searched using information such as:

- Loan ID
- Customer information

A `HashMap` is used for efficient Loan ID-based searching.

---

## 8. Report Generation

The system provides loan-related reports.

Reports can display:

- Customer information
- Loan information
- Loan status
- EMI information
- Repayment information
- Overdue EMIs

---

# 🧑‍💻 OOP Concepts Used

The project demonstrates important Java Object-Oriented Programming concepts.

## Encapsulation

Customer and loan data is kept inside classes using private variables.

Example:

```java
private String name;
private double income;
private int creditScore;