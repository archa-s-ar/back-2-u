# Back2U

### There’s Always a Way Back.

> **A Smart Campus Lost & Found System**

Back2U is a **Java Swing-based Smart Campus Lost & Found System** designed to help students report lost and found belongings, identify potential matches, verify ownership, and complete a secure handover.

Instead of depending on scattered WhatsApp groups, class groups, notice boards, or word of mouth, Back2U provides a centralized system that manages the complete journey from **reporting an item to returning it to its rightful owner**.

---

## 📌 Table of Contents

- [Problem Statement](#-problem-statement)
- [Proposed Solution](#-proposed-solution)
- [Objectives](#-objectives)
- [Key Features](#-key-features)
- [System Workflow](#-system-workflow)
- [Smart Matching](#-smart-matching)
- [Ownership Verification](#-ownership-verification)
- [Notifications](#-notifications)
- [Secure Handover](#-secure-handover)
- [Admin Module](#-admin-module)
- [User Roles](#-user-roles)
- [System Architecture](#-system-architecture)
- [Database](#-database)
- [OOP Concepts](#-oop-concepts)
- [Design Patterns](#-design-patterns)
- [Technology Stack](#-technology-stack)
- [Project Structure](#-project-structure)
- [Item Lifecycle](#-item-lifecycle)
- [Security and Access Control](#-security-and-access-control)
- [Testing](#-testing)
- [Future Scope](#-future-scope)
- [Team](#-team)
- [Project Status](#-project-status)

---

## 📌 Problem Statement

Students frequently lose personal belongings such as:

- ID cards
- Wallets
- Phones
- Earphones
- Books
- Laptops
- Other personal items

On a college campus, information about found items is often shared through different WhatsApp groups, class groups, notice boards, or word of mouth.

This creates several problems:

- No centralized Lost & Found system
- Difficult to search through multiple reports
- Lost and found reports may not reach the right person
- Manual comparison of reports is time-consuming
- Similar items can lead to incorrect ownership claims
- No structured ownership verification
- No standardized handover process
- Difficult to track an item's complete lifecycle

Back2U addresses these problems through a centralized and structured campus Lost & Found system.

---

## 💡 Proposed Solution

Back2U provides a centralized platform where students can:

1. Report lost items.
2. Report found items.
3. Automatically compare lost and found reports.
4. Calculate a weighted match score.
5. Notify potential owners when a match is found.
6. Verify ownership using private identifying details.
7. Arrange a structured handover.
8. Use a temporary handover PIN to confirm the exchange.
9. Track the item until it is successfully returned.

The system also provides an **Admin module** for handling exceptional cases such as failed verification, multiple claims, disputes, and suspicious reports.

---

## 🎯 Objectives

Back2U aims to:

- Centralize campus Lost & Found activities.
- Reduce the time required to locate lost belongings.
- Connect lost and found reports using multiple matching factors.
- Notify potential owners about relevant matches.
- Reduce false ownership claims.
- Provide a structured ownership verification process.
- Provide a secure handover mechanism.
- Track the complete lifecycle of an item.
- Provide administrative supervision for exceptional cases.

---

## ✨ Key Features

### 🔐 User Authentication

- Student registration
- Student login
- Admin login
- Role-based access control
- Student and Admin dashboards

### 📦 Report Lost Item

Students can report a lost item by providing:

- Item category
- Item name
- Description
- Location
- Date
- Approximate time
- Private identifying details

### 🔎 Report Found Item

Students who find an item can report:

- Item category
- Item name
- Description
- Found location
- Date
- Approximate time
- Additional notes

### 🧠 Smart Matching

Back2U compares lost and found reports using four factors:

| Matching Factor | Weight |
|---|---:|
| Description | **35%** |
| Location | **30%** |
| Time | **20%** |
| Category | **15%** |
| **Total** | **100%** |

---

## 📐 Matching Formula

$$
Match\ Score =
(D \times 0.35)
+
(L \times 0.30)
+
(T \times 0.20)
+
(C \times 0.15)
$$

Where:

- `D` = Description similarity score
- `L` = Location similarity score
- `T` = Time similarity score
- `C` = Category similarity score

Each individual factor is normalized between `0` and `1`.

### Example

```text
Description = 0.90
Location    = 1.00
Time        = 0.90
Category    = 1.00

Match Score
= (0.90 × 0.35)
+ (1.00 × 0.30)
+ (0.90 × 0.20)
+ (1.00 × 0.15)

= 0.945

= 94.5%
```

> **Important:** The match score indicates similarity between the lost and found reports. It does **not** by itself prove ownership.

---

## 🔔 Notifications

When a sufficiently strong potential match is identified, Back2U generates an **in-app notification** for the reported owner.

Example:

> **Potential Match Found**  
> Your black wireless earbuds may have been found.

A notification can contain:

- Notification ID
- Recipient
- Title
- Message
- Read/unread status
- Timestamp

---

## 🔐 Ownership Verification

Back2U separates **matching** from **ownership verification**.

A high match score only indicates that the lost and found reports appear similar.

The owner provides private identifying details while reporting the lost item.

Example:

```text
• Small scratch on the charging case
• Blue sticker inside the case
• Small dent on the left earbud
```

When a potential match is found:

```text
Potential Match
       ↓
Finder receives relevant private details
       ↓
Finder physically checks the item
       ↓
       ┌──────────────────┐
       │                  │
DETAILS MATCH      DETAILS DON'T MATCH
       │                  │
       ↓                  ↓
   VERIFIED             REJECTED
```

---

## 🔑 Secure Handover

After successful ownership verification:

1. Owner and finder arrange a handover location.
2. The system generates a temporary 6-digit PIN.
3. The owner receives the PIN.
4. The physical item is handed over.
5. The owner provides the PIN after receiving the item.
6. The finder enters the PIN.
7. The system validates the PIN.
8. If valid, the item is marked as `RETURNED`.

```text
VERIFIED
   ↓
ARRANGE HANDOVER
   ↓
GENERATE PIN
   ↓
PHYSICAL HANDOVER
   ↓
ENTER PIN
   ↓
VALIDATE PIN
   ↓
RETURNED
```

---

## 👨‍💼 Admin Module

The Admin module is primarily used for **supervision and exception handling**.

Administrators can:

- Manage users
- Monitor lost reports
- Monitor found reports
- View potential matches
- Review failed verification
- Review multiple claims
- Handle disputes
- Review suspicious cases
- Monitor handovers
- View system statistics

---

## 👥 User Roles

### Student

A student can:

- Create an account
- Log in
- Report lost items
- Report found items
- View their reports
- View potential matches
- Receive notifications
- Participate in ownership verification
- Arrange handover
- Complete handover

### Admin

An administrator has additional permissions to:

- Manage users
- Monitor reports
- Review problematic cases
- Handle disputes
- Review failed verification
- Monitor handovers
- View analytics

---

## 🏗️ System Architecture

Back2U follows a layered architecture.

```text
┌─────────────────────────────┐
│        Java Swing UI        │
│ Login / Dashboard / Forms   │
└──────────────┬──────────────┘
               ↓
┌─────────────────────────────┐
│    Service / Business       │
│ Matching / Verification /   │
│ Notification / Handover     │
└──────────────┬──────────────┘
               ↓
┌─────────────────────────────┐
│            DAO              │
│ UserDAO / LostItemDAO /     │
│ FoundItemDAO / etc.         │
└──────────────┬──────────────┘
               ↓
┌─────────────────────────────┐
│            JDBC             │
└──────────────┬──────────────┘
               ↓
┌─────────────────────────────┐
│           MySQL             │
└─────────────────────────────┘
```

---

## 🧩 OOP Concepts

### Encapsulation

```java
private String description;

public String getDescription() {
    return description;
}

public void setDescription(String description) {
    this.description = description;
}
```

### Inheritance

```text
        User
       /    \
  Student   Admin
```

```java
class Student extends User {
}

class Admin extends User {
}
```

### Abstraction

The matching system uses a common `Matcher` abstraction:

```java
interface Matcher {
    double calculate(LostItem lost, FoundItem found);
}
```

### Interface

`Matcher` defines the common contract for matching strategies.

### Polymorphism

```java
Matcher matcher = new DescriptionMatcher();
```

The same `Matcher` reference can refer to different matcher implementations.

### Method Overriding

Each concrete matcher provides its own implementation of `calculate()`.

### Method Overloading

Methods with the same name can be defined with different parameter lists where required.

---

## 🎨 Design Patterns

### Strategy Pattern

Used in the matching system.

```text
                  Matcher
                     │
       ┌─────────────┼─────────────┐
       ↓             ↓             ↓
Description      Location         Time
Matcher          Matcher          Matcher
                     +
                CategoryMatcher
```

Each matcher represents a different matching strategy.

### DAO Pattern

Used to separate database operations from business logic.

```text
Swing UI
   ↓
Service
   ↓
DAO
   ↓
JDBC
   ↓
MySQL
```

Examples:

```text
UserDAO
LostItemDAO
FoundItemDAO
NotificationDAO
HandoverDAO
```

### Singleton Pattern

A Singleton can be used for services that should have a single shared instance throughout the application, such as `NotificationService`.

> **Only patterns that are actually implemented in the final codebase should be claimed as implemented design patterns.**

---

## 🔒 Security and Access Control

Back2U separates **authentication** from **authorization**.

### Authentication

Determines who the user is.

```text
Email + Password
       ↓
Authentication
```

### Authorization

Determines what the user is allowed to do.

```text
              Logged-in User
                    ↓
                Check Role
                 /       \
                ↓         ↓
            Student      Admin
                ↓         ↓
         Student UI    Admin UI
```

Admin operations should be protected by authorization checks in the application/service layer, not only by hiding buttons in the GUI.

---

## 🕵️ Privacy Model

Back2U distinguishes between **public report information** and **private verification information**.

### Public Information

Used for matching:

- Category
- Item name
- Description
- Location
- Date
- Time

### Private Information

Used for ownership verification:

- Unique scratches
- Stickers
- Engravings
- Dents
- Other private physical characteristics

---

## 🗄️ Database

Back2U uses **MySQL** for persistent data storage.

### Core Tables

```text
accountdetails
login
lost_item
found_item
Handover_pin
```

Additional tables such as `match`, `verification`, and `notification` can be introduced depending on the final implementation.

> The exact final database schema should match the actual SQL files in the project.

---

## 🔄 Item Lifecycle

```text
REPORTED
    ↓
SEARCHING
    ↓
MATCHED
    ↓
VERIFICATION
    ↓
VERIFIED
    ↓
HANDOVER
    ↓
RETURNED
    ↓
CLOSED
```

If verification fails:

```text
VERIFICATION
      ↓
   REJECTED
      ↓
Retry / Admin Review
```

---

## 🔎 Matching vs Verification vs Handover

| Stage | Purpose |
|---|---|
| **Matching** | Determines whether a lost and found report may refer to the same item |
| **Verification** | Confirms ownership by comparing private identifying details with the physical item |
| **Handover** | Confirms that the verified item was actually returned |

In simple terms:

```text
MATCHING
"Could these be the same item?"

        ↓

VERIFICATION
"Does the physical item match the owner's private details?"

        ↓

HANDOVER
"Was the verified item actually returned?"
```

---

## 🧪 Testing

Back2U should be tested using both normal and exceptional workflows.

### Successful Flow

```text
Student 1 reports lost item
          ↓
Student 2 reports found item
          ↓
Matching engine calculates score
          ↓
Potential match generated
          ↓
Owner notified
          ↓
Finder verifies physical item
          ↓
Ownership verified
          ↓
Handover arranged
          ↓
PIN validated
          ↓
Item marked RETURNED
```

### Failed Verification

```text
Match Found
    ↓
Verification
    ↓
Details Don't Match
    ↓
Rejected
    ↓
Retry / Admin Review
```

### Invalid Handover PIN

```text
Handover
   ↓
Invalid PIN
   ↓
Item remains in HANDOVER state
```

### Expired PIN

```text
Handover
   ↓
PIN Expired
   ↓
Generate New PIN
```

### Unauthorized Access

```text
Student
   ↓
Attempts Admin Operation
   ↓
Authorization Check
   ↓
Access Denied
```

Other test cases include:

- Empty form fields
- Invalid login credentials
- Duplicate registration
- Invalid email
- Database connection failure
- Multiple potential matches
- Logout and re-login
- Invalid PIN
- Expired PIN
- Failed verification
- Admin access control

---

## 🛠️ Technology Stack

| Technology | Purpose |
|---|---|
| **Java** | Application logic |
| **Java Swing** | Desktop GUI |
| **JDBC** | Java–MySQL connectivity |
| **MySQL** | Database |
| **Git** | Version control |
| **GitHub** | Collaboration and source control |

---

## 📁 Project Structure

```text
Back2U/
│
├── src/
│   └── back2u/
│       ├── model/
│       │   ├── User.java
│       │   ├── Student.java
│       │   ├── Admin.java
│       │   ├── LostItem.java
│       │   ├── FoundItem.java
│       │   ├── MatchResult.java
│       │   ├── Notification.java
│       │   └── Handover.java
│       │
│       ├── matching/
│       │   ├── Matcher.java
│       │   ├── DescriptionMatcher.java
│       │   ├── LocationMatcher.java
│       │   ├── TimeMatcher.java
│       │   ├── CategoryMatcher.java
│       │   └── MatchingEngine.java
│       │
│       ├── dao/
│       │   ├── UserDAO.java
│       │   ├── LostItemDAO.java
│       │   ├── FoundItemDAO.java
│       │   ├── NotificationDAO.java
│       │   └── HandoverDAO.java
│       │
│       ├── service/
│       │   ├── NotificationService.java
│       │   ├── VerificationService.java
│       │   └── HandoverService.java
│       │
│       ├── ui/
│       │   ├── LoginFrame.java
│       │   ├── SignupFrame.java
│       │   ├── StudentDashboard.java
│       │   └── AdminDashboard.java
│       │
│       └── util/
│           └── DatabaseConnection.java
│
├── database/
│   └── back2u.sql
│
├── README.md
└── .gitignore
```

---

## 🚀 Future Scope

Back2U can be extended with:

- Image-based item similarity
- Advanced natural-language matching
- Mobile application
- Email notifications
- Push notifications
- QR-based item identification
- Campus-wide deployment
- Frequently lost location analytics
- Improved duplicate-claim detection
- Advanced fraud detection

---

## 👥 Team

### Project

**Back2U — Smart Campus Lost & Found System**

### Tagline

> **There’s Always a Way Back.**

### Contributors

- **[Member 1]**
- **[Member 2]**
- **[Member 3]**
- **[Member 4]**

---

## 📊 Project Status

**Status:** 🚧 Under Development

Back2U is being developed as an academic Java Swing project focusing on:

- Object-Oriented Programming
- Java Swing
- JDBC
- MySQL
- Design Patterns
- Database Management
- Role-Based Access Control
- Practical campus problem solving

---

## ❤️ Why Back2U?

Losing something on campus shouldn't mean losing it forever.

Back2U provides a structured way to **report, match, verify, hand over, and return** lost belongings.

### **Back2U — There’s Always a Way Back.**
