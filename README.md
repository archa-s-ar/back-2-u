# Back2U

### *There’s Always a Way Back.*

**Back2U** is a smart campus Lost & Found system that helps students reconnect with their lost belongings through **multi-factor matching, private ownership verification, and secure handover**.

> Lost doesn't have to mean gone.

---

## 📌 Overview

Traditional Lost & Found systems usually work like a simple notice board:

**Lost item → Found item → Contact the person**

Back2U goes a step further.

When a found item is reported, the system compares it against existing lost-item reports using multiple factors:

* **Description — 35%**
* **Location — 30%**
* **Time — 20%**
* **Category — 15%**

A high-scoring match creates a potential-match notification.

However, **matching is not the same as proving ownership**.

Back2U therefore uses a second layer of **private ownership verification**.

The original owner can provide private characteristics of the lost item when submitting the report. These details are stored securely and are never directly revealed to the finder.

If a strong match is found, Back2U converts the owner's private details into neutral inspection questions for the finder.

For example:

> Owner's private clue:
> **"Small scratch on the charging case."**

The finder sees:

> **"Does the charging case have a distinctive scratch or mark?"**

The finder answers based on the physical item, and Back2U compares the observation with the owner's original information.

This separates:

**"Does this look like the same item?"**

from:

**"Does the physical item contain characteristics known by the real owner?"**

---

## ✨ Key Features

### 🔎 Smart Multi-Factor Matching

Back2U calculates a match score using:

| Factor      | Weight |
| ----------- | -----: |
| Description |    35% |
| Location    |    30% |
| Time        |    20% |
| Category    |    15% |

Example:

```text
Description  → 31.5 / 35
Location     → 27.0 / 30
Time         → 20.0 / 20
Category     → 15.0 / 15
────────────────────────
Match Score  → 93.5%
```

Multiple candidate matches can be returned and ranked by match score.

---

### 🔐 Private Ownership Verification

Owners can provide characteristics that are difficult for someone else to guess.

Examples:

* Scratch or dent
* Sticker
* Unique marking
* Physical feature
* Case characteristic
* Other private identifying detail

These details remain hidden from the finder.

Back2U uses predefined verification-question templates to convert them into neutral questions.

```text
Private owner clue
        ↓
Question Template
        ↓
Neutral inspection question
        ↓
Finder observes physical item
        ↓
YES / NO / NOT SURE
        ↓
Back2U compares results
```

**The finder never sees the original owner-provided answer.**

---

### 🔔 Match Notifications

When a potential match crosses the configured threshold:

```text
Found Item Submitted
        ↓
Matching Engine
        ↓
High Match Score
        ↓
MATCH_FOUND notification
        ↓
Owner Dashboard
```

Notifications are stored in the system and displayed through the application's notification interface.

---

### 🤝 Secure Handover

After ownership verification succeeds:

1. Owner and finder arrange a handover.
2. Both confirm the handover details.
3. Back2U generates a temporary handover code.
4. The owner receives the item.
5. The owner provides the code.
6. The finder enters the code.
7. The system validates the code.
8. The item is marked **RETURNED**.

---

### 🛡️ Admin Oversight

Administrators can intervene when necessary.

The admin dashboard provides:

* Match review
* Flagged-report review
* User management
* Handover oversight
* Match analytics
* Recovery-time analytics
* Flagged-report trends
* Handover completion metrics

---

## 🔄 How Back2U Works

```text
┌─────────────────────┐
│   REPORT LOST ITEM  │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│   REPORT FOUND ITEM │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│  SMART MATCHING     │
│                     │
│ Description  35%    │
│ Location     30%    │
│ Time         20%    │
│ Category     15%    │
└──────────┬──────────┘
           │
      High Match?
       /        \
     No          Yes
     │            │
     ▼            ▼
  Continue     Notify Owner
                  │
                  ▼
        ┌────────────────────┐
        │ OWNERSHIP          │
        │ VERIFICATION       │
        └─────────┬──────────┘
                  │
                  ▼
        Finder inspects item
                  │
                  ▼
        Questions generated
        from owner's clues
                  │
          ┌───────┴───────┐
          │               │
       Verified        Failed
          │               │
          ▼               ▼
      HANDOVER       Retry / Review
          │
          ▼
    Temporary Code
          │
          ▼
       RETURNED
          │
          ▼
        CLOSED
```

---

## 🧠 Matching vs Verification

One of the core design principles of Back2U is that **matching and verification are separate processes**.

### Match Score

Answers:

> **"Could these two reports refer to the same item?"**

Uses:

* Description
* Location
* Time
* Category

### Ownership Verification

Answers:

> **"Does the physical found item have characteristics known privately by the original owner?"**

Uses:

* Owner-provided private clues
* Neutral inspection questions
* Finder observations

### Handover Verification

Answers:

> **"Was the verified item actually returned?"**

Uses:

* Both-party confirmation
* Temporary handover code
* Handover status

---

## 🗃️ Item Lifecycle

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

## 🏗️ System Architecture

```text
┌─────────────────────────────────────────┐
│             Java Swing GUI              │
│                                         │
│  Student Dashboard    Admin Dashboard  │
│  Lost Reports         Match Review      │
│  Found Reports        User Management   │
│  Notifications        Handover Review  │
└───────────────────┬─────────────────────┘
                    │
                    ▼
┌─────────────────────────────────────────┐
│             Application Layer            │
│                                         │
│  MatchingEngine                          │
│  VerificationService                     │
│  NotificationService                     │
│  HandoverService                         │
│  UserService                             │
└───────────────────┬─────────────────────┘
                    │
                    ▼
┌─────────────────────────────────────────┐
│              Data Access Layer           │
│                                         │
│  LostItemDAO                             │
│  FoundItemDAO                            │
│  NotificationDAO                         │
│  UserDAO                                 │
│  VerificationDAO                         │
│  HandoverDAO                             │
└───────────────────┬─────────────────────┘
                    │
                    ▼
┌─────────────────────────────────────────┐
│                Database                  │
│                                         │
│ Users                                    │
│ Lost Items                               │
│ Found Items                              │
│ Private Verification Data               │
│ Matches                                  │
│ Notifications                            │
│ Handovers                                │
└─────────────────────────────────────────┘
```

---

## 💻 Technology Stack

| Technology       | Purpose                 |
| ---------------- | ----------------------- |
| **Java**         | Core application        |
| **Java Swing**   | Desktop GUI             |
| **JDBC**         | Database connectivity   |
| **SQL**          | Persistent data storage |
| **Git & GitHub** | Version control         |

### No external AI is required

The smart matching system is implemented using deterministic Java logic.

This makes the system:

* Explainable
* Predictable
* Testable
* Suitable for a Java-only academic project

---

## 🧩 Object-Oriented Design

Back2U is designed to demonstrate core Object-Oriented Programming concepts.

### Classes & Objects

Examples:

```text
User
LostItem
FoundItem
MatchResult
Notification
VerificationQuestion
Handover
```

### Inheritance

Possible hierarchy:

```text
              User
             /    \
        Student    Admin
```

### Abstraction

Services and matching components can expose abstract behaviour while hiding implementation details.

### Interfaces

Examples:

```text
Matcher
NotificationObserver
DAO
VerificationStrategy
```

### Method Overloading

Different methods can process matching or verification operations with different parameters.

### Method Overriding

Specialized user or service classes can override inherited behaviour.

---

## 🎨 Design Patterns

Back2U can demonstrate several design patterns naturally.

### Strategy Pattern

Different matching strategies can implement a common interface:

```text
Matcher
   ├── DescriptionMatcher
   ├── LocationMatcher
   ├── TimeMatcher
   └── CategoryMatcher
```

### Factory Pattern

A `VerificationQuestionFactory` can convert owner-provided clues into appropriate question templates.

```text
Owner Clue
    ↓
VerificationQuestionFactory
    ↓
VerificationQuestion
```

### Observer Pattern

The notification system can notify dashboards when a new match or status change occurs.

```text
MatchingService
       │
       ▼
Notification System
       │
       ├── Owner Dashboard
       └── Finder Dashboard
```

### DAO Pattern

Database operations are separated from application logic through Data Access Objects.

---

## 🔒 Privacy Model

Back2U separates **public report information** from **private ownership information**.

### Public

```text
Item name
Category
Description
Location
Date
Approximate time
```

### Private

```text
Unique marks
Physical characteristics
Private identifiers
Owner verification clues
```

Private information is never directly shown to the finder.

---

## 📊 Example

### Lost Report

```text
Item:
Black Wireless Earbuds

Location:
Library

Time:
3:00 PM

Description:
Black earbuds in a small charging case
```

### Private Verification Details

```text
Scratch:
Small scratch on charging case

Sticker:
Blue sticker inside case

Physical feature:
Small dent on left earbud
```

### Found Report

```text
Item:
Black wireless earbuds

Location:
Library staircase

Time:
3:15 PM

Description:
Black wireless earbuds in a small case
```

### Match

```text
Description   31.5 / 35
Location      27.0 / 30
Time          20.0 / 20
Category      15.0 / 15

MATCH SCORE: 93.5%
```

### Verification

The finder receives:

```text
Q1. Does the charging case have a distinctive scratch or mark?

Q2. Is there a sticker or marking inside the case?

Q3. Does the left earbud have a noticeable dent or damage?
```

The original private answers remain hidden.

---

## 🚀 Getting Started

### Prerequisites

* Java Development Kit (JDK)
* Java-compatible IDE such as IntelliJ IDEA, Eclipse, or NetBeans
* SQL database
* JDBC driver for the selected database

### Clone the repository

```bash
git clone <YOUR_REPOSITORY_URL>
cd Back2U
```

### Configure the database

Create the Back2U database and configure the JDBC connection according to the project's database configuration.

### Run

Open the project in your preferred Java IDE and run the application's main class.

---

## 📁 Suggested Project Structure

```text
Back2U/
│
├── src/
│   ├── model/
│   │   ├── User.java
│   │   ├── Student.java
│   │   ├── Admin.java
│   │   ├── LostItem.java
│   │   ├── FoundItem.java
│   │   ├── MatchResult.java
│   │   ├── Notification.java
│   │   └── Handover.java
│   │
│   ├── matching/
│   │   ├── Matcher.java
│   │   ├── MatchingEngine.java
│   │   ├── DescriptionMatcher.java
│   │   ├── LocationMatcher.java
│   │   ├── TimeMatcher.java
│   │   └── CategoryMatcher.java
│   │
│   ├── verification/
│   │   ├── VerificationService.java
│   │   ├── VerificationQuestion.java
│   │   └── VerificationQuestionFactory.java
│   │
│   ├── notification/
│   │   └── NotificationService.java
│   │
│   ├── handover/
│   │   └── HandoverService.java
│   │
│   ├── dao/
│   │   ├── UserDAO.java
│   │   ├── LostItemDAO.java
│   │   ├── FoundItemDAO.java
│   │   └── NotificationDAO.java
│   │
│   ├── ui/
│   │   ├── LoginFrame.java
│   │   ├── StudentDashboard.java
│   │   ├── AdminDashboard.java
│   │   └── ...
│   │
│   └── util/
│       └── DatabaseConnection.java
│
├── resources/
├── database/
│   └── schema.sql
│
├── screenshots/
│
└── README.md
```

---

## 🗺️ Roadmap

* [x] Product concept
* [x] Smart matching model
* [x] Private verification concept
* [x] Handover workflow
* [x] Admin workflow
* [x] UI/UX design
* [x] Figma prototype
* [ ] Database implementation
* [ ] Matching engine implementation
* [ ] Verification-question factory
* [ ] Notification system
* [ ] Handover-code system
* [ ] Admin analytics
* [ ] Integration testing
* [ ] Final Java Swing application

---

## 🎯 Project Goals

Back2U aims to demonstrate how a campus Lost & Found system can move beyond simple CRUD operations by combining:

**Intelligent matching + privacy-aware verification + secure handover**

while maintaining a fully explainable Java-based architecture.

---

## 📌 Project Status

**Status:** In Development

**Platform:** Java Desktop Application

**Interface:** Java Swing

**Domain:** Smart Campus Lost & Found

---

## 👥 Contributors



```text
Team GRAPHINES - Adhithya K | Archa S | Gitto George | Sreedurga P
RIT Kottayam
B.Tech Computer Science & Engineering
```

---

## 📄 License

MIT LICENSE

---

### Back2U

> **There’s Always a Way Back.**
