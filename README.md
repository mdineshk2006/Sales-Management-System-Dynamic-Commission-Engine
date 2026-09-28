# Sales Management System with Dynamic Commission Engine

A Core Java console application that manages salespersons, products, and
sales transactions while **dynamically calculating commission** based on
the type of salesperson who made the sale.

Built for CIE-2 (Skill Development Laboratory using Java) — demonstrating
Unit III (Polymorphism, Interfaces, Abstract Classes) and Unit IV
(Exception Handling) through a single, practical application.

---

## Problem Statement

Sales organizations employ different types of salespeople — Junior,
Senior, and Manager — each with a different commission rate. Calculating
commission manually is error-prone and makes it hard to change policy
later. This system automates commission calculation using a **Dynamic
Commission Engine** that determines the correct rate at runtime based on
the salesperson's actual type, and validates every transaction using
custom exception handling.

---

## Objectives

- Manage salespersons, products, and sales transactions through a console menu.
- Calculate commission dynamically depending on salesperson type, using polymorphism.
- Validate all user input and transactions using custom, user-defined exceptions.
- Demonstrate abstract classes, interfaces, method overriding, method overloading,
  and `try`/`catch`/`finally`/`throw`/`throws` in a realistic, working application.

---

## Features

- **Salesperson Management** — add Junior / Senior / Manager salespersons, view all.
- **Product Management** — add products with price and stock, view all.
- **Sales Management** — create a sale (with an optional 10% discount), auto-deduct stock.
- **Dynamic Commission Engine** — commission is calculated using the correct
  overridden method for the salesperson's actual type.
- **Exception Handling** — invalid quantity, insufficient stock, employee not found,
  product not found, and invalid sales amount are all handled gracefully without
  crashing the program.
- **Sales History** — view every transaction recorded so far.

---

## Technologies Used

- Java (JDK 17+)
- Core Java only — no external libraries or frameworks
- Git & GitHub for version control

---

## Java Concepts Used

| Concept | Where it's used |
|---|---|
| **Abstract class** | `SalesPerson` — common employee fields + abstract `calculateCommission()` |
| **Inheritance** | `JuniorSalesPerson`, `SeniorSalesPerson`, `SalesManager` extend `SalesPerson` |
| **Method Overriding** | Each subclass overrides `calculateCommission()` and `getRole()` |
| **Runtime Polymorphism / Dynamic Binding** | `CommissionEngine` calls `calculateCommission()` through a `Commissionable` reference — the actual overridden method resolves at runtime |
| **Method Overloading** | `SalesService.createSale(id, id, qty)` and `createSale(id, id, qty, applyDiscount)` |
| **Interface** | `Commissionable` — implemented by `SalesPerson` |
| **Custom (user-defined) Exceptions** | `EmployeeNotFoundException`, `ProductNotFoundException`, `InvalidQuantityException`, `InvalidSalesAmountException` |
| **try / catch / finally** | Used throughout `Main.java` for every menu operation |
| **throw / throws** | Used in `SalesService`, `CommissionEngine`, and their calling methods |

---

## Project Structure

```
Sales-Management-System-Dynamic-Commission-Engine/
│
├── src/
│   ├── model/
│   │   ├── SalesPerson.java          (abstract class, implements Commissionable)
│   │   ├── JuniorSalesPerson.java
│   │   ├── SeniorSalesPerson.java
│   │   ├── SalesManager.java
│   │   ├── Product.java
│   │   └── Sale.java
│   │
│   ├── service/
│   │   ├── Commissionable.java       (interface)
│   │   ├── CommissionEngine.java
│   │   └── SalesService.java
│   │
│   ├── exception/
│   │   ├── EmployeeNotFoundException.java
│   │   ├── ProductNotFoundException.java
│   │   ├── InvalidQuantityException.java
│   │   └── InvalidSalesAmountException.java
│   │
│   └── Main.java
│
└── README.md
```

---

## Class Design

```
                    SalesPerson
              (Abstract, implements Commissionable)
                         |
          -------------------------------
          |              |              |
   JuniorSalesPerson SeniorSalesPerson SalesManager
     (5% commission)  (8% commission)  (10% commission)
          |              |              |
          --------------------------------
                         |
              calculateCommission()  <-- Method Overriding


        Commissionable (interface)
                 |
     calculateCommission(salesAmount)
                 |
          CommissionEngine
   (depends only on the interface,
    not on concrete subclasses)


Product                          Sale
 - productId                      - saleId
 - productName                    - salesperson
 - price                          - product
 - stock                          - quantity
                                  - totalAmount
                                  - commission
```

---

## How to Run

1. Make sure JDK 17 or later is installed.
2. Clone the repository and navigate into the `src` folder:
   ```bash
   cd Sales-Management-System-Dynamic-Commission-Engine/src
   ```
3. Compile all source files:
   ```bash
   javac -d ../out model/*.java service/*.java exception/*.java Main.java
   ```
4. Run the application:
   ```bash
   java -cp ../out Main
   ```

The application preloads 3 sample salespersons (`E101` Junior, `E102`
Senior, `E103` Manager) and 3 sample products (`P201` Laptop, `P202`
Mouse, `P203` Keyboard) so you can test immediately without manual entry.

---

## Sample Output

**Valid Sale:**
```
Enter Employee ID: E103
Enter Product ID: P201
Enter Quantity: 2
Apply 10% discount? (y/n): n

Sale created successfully!
----------------------------------------
Sale ID          : 1001
Salesperson      : Anita (Sales Manager)
Product          : Laptop
Quantity         : 2
Discount Applied : No
Total Amount     : Rs. 100000.0
Commission       : Rs. 10000.0
----------------------------------------
[Sale transaction processing completed]
```

**Exception Handling — Invalid Quantity:**
```
Enter Quantity: -5
Error: Quantity must be greater than zero.
[Sale transaction processing completed]
```

**Exception Handling — Employee Not Found:**
```
Enter Employee ID: E999
Error: Employee not found with ID: E999
[Sale transaction processing completed]
```

**Exception Handling — Insufficient Stock:**
```
Enter Product ID: P203
Enter Quantity: 100
Error: Insufficient stock for product 'Keyboard'. Available stock: 30
[Sale transaction processing completed]
```

---

## Exception Handling Summary

| Scenario | Exception Thrown |
|---|---|
| Quantity is zero or negative | `InvalidQuantityException` |
| Requested quantity exceeds available stock | `InvalidQuantityException` |
| Employee ID does not exist | `EmployeeNotFoundException` |
| Product ID does not exist | `ProductNotFoundException` |
| Sales amount is zero or negative when computing commission | `InvalidSalesAmountException` |

Every sale attempt is wrapped in `try` / `catch` blocks in `Main.java`,
with a `finally` block that always prints a transaction-completion
message — regardless of whether the sale succeeded or an exception
occurred.

---

## Future Enhancements

- Add Java Collections-based persistence and file handling (save/load data)
- Integrate a MySQL database using JDBC
- Rebuild the backend using Spring Boot
- Expose functionality via REST APIs
- Build a web-based dashboard for sales and commission analytics

---

## Author(s)

Group members — CIE-2, Skill Development Laboratory using Java
*(Add names and roll numbers here before submission.)*
