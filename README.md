# Back2U

### There’s Always a Way Back.

**A Smart Campus Lost & Found System**

Back2U is a Java Swing desktop application that helps students report lost and found belongings, identify possible matches, review ownership details, and coordinate the return of recovered items.

Instead of relying on scattered WhatsApp groups, class groups, notice boards, or word of mouth, Back2U provides a centralized workflow for reporting items, reviewing possible matches, confirming a match, and arranging a handover.

> **Project status:** Completed

---

## Table of Contents

- [Problem Statement](#problem-statement)
- [Objectives](#objectives)
- [Features](#features)
- [Workflow](#workflow)
- [Matching System](#matching-system)
- [Ownership Verification and Privacy](#ownership-verification-and-privacy)
- [Handover Process](#handover-process)
- [Admin Module](#admin-module)
- [Technology Stack](#technology-stack)
- [Database](#database)
- [Project Structure](#project-structure)
- [Setup and Running](#setup-and-running)
- [Security Notes](#security-notes)
- [Testing Checklist](#testing-checklist)
- [Future Scope](#future-scope)
- [Team](#team)
- [Project Status](#project-status)

---

## Problem Statement

Students often lose personal belongings such as ID cards, wallets, phones, earphones, books, and keys. Information about found items is frequently scattered across group chats, notice boards, or word of mouth.

This makes it difficult to search reports, connect the right owner with the person who found an item, prevent false claims, and track whether an item has been returned.

Back2U aims to make this process more organized through a centralized campus Lost & Found application.

## Objectives

- Centralize lost and found reports.
- Help users discover possible matches.
- Allow both parties to review and respond to a potential match.
- Support ownership verification using private item details.
- Provide a structured handover arrangement.
- Track match and handover status.
- Provide an admin interface for reviewing reports and exceptional cases.

## Features

### User accounts
- Student registration and login.
- User-facing dashboard.
- Admin-facing pages are included in the project; the exact permissions should be verified against the current implementation.

### Report lost and found items
Reports use fields such as item name, category, description, date, time, location, and report type. Lost-item reports can also include unique identifying details to help distinguish similar items.

### Possible matches and notifications
- Compare lost reports with found reports.
- Display possible matches and a match score.
- Show the user's role in a match (owner or finder).
- Allow users to confirm a possible match or reject it as not a match.
- Refresh the notifications list and check match status.

### Match details
The details screen displays information about the lost item and, when available, contact details associated with the found-item report.

### Handover arrangements
Once both parties confirm a match, the owner can create an arrangement containing:
- Meeting location
- Meeting date
- Meeting time
- Instructions
- A generated six-digit PIN

The handover record is stored in MySQL and is associated with a match. The intended completion workflow verifies the PIN and updates the relevant handover, match, and item statuses. Test this workflow end to end before treating it as complete.

### Admin pages
The repository includes admin-related classes such as `AdminDashboard.java` and `AdminReport.java`. The available actions and access controls should be verified in the running application.

---

## Workflow

1. A student signs in.
2. A student reports a lost item or a found item.
3. The matching service compares eligible lost and found reports.
4. Users review possible matches in Notifications.
5. The owner and finder independently confirm or reject the match.
6. When both confirm, the match moves to verification.
7. The owner creates a handover arrangement.
8. The finder views the arrangement and enters the PIN after the item has been returned.
9. The application should mark the handover, match, and both associated item reports as completed.

**Important:** A match score is only an indication of similarity; it is not proof of ownership. A completed match confirmation is also not the same as a completed physical handover.

## Matching System

The project includes `ItemMatcher.java` and `ItemMatchingService.java`.

The matching logic considers several characteristics of lost and found reports, such as item name, description, location, date/time, category, and unique details. Candidate matches are scored, and eligible candidates can be stored in `item_matches`.

The exact weights and minimum score are defined by the current source code. Keep this section synchronized with `ItemMatcher.java` if the algorithm changes.

## Ownership Verification and Privacy

Matching and ownership verification are separate stages. Similar descriptions or locations can produce a possible match, but users should confirm that the physical item matches private identifying details before proceeding.

Examples of useful private details include:
- A small scratch or dent
- A sticker in a specific location
- An engraving or distinctive mark
- Another characteristic not included in the public report

Private details and personal contact information should only be shown to users who need them for the verification process.

## Handover Process

The intended handover flow is:

```text
Both parties confirm match
          |
          v
      Verification
          |
          v
Owner creates arrangement
(location, date, time, instructions)
          |
          v
   Six-digit PIN generated
          |
          v
Physical item is returned
          |
          v
Finder enters PIN
          |
          v
PIN is validated
          |
          v
Handover and related records
are marked completed
```

The database table `handover` stores the meeting details, PIN, status, and return timestamp. The `match_id` links the arrangement to `item_matches`.

## Admin Module

Admin-related source files include `AdminDashboard.java` and `AdminReport.java`. The project intends to support administrative review of reports and exceptional cases. Confirm each admin operation and its authorization checks before relying on it.

## Technology Stack

| Technology | Purpose |
|---|---|
| Java | Application logic |
| Java Swing | Desktop user interface |
| JDBC | Java-to-MySQL connectivity |
| MySQL | Persistent storage |
| Git | Version control |
| GitHub | Repository hosting and collaboration |
| NetBeans | Development environment used during development |

## Database

The current database schema file is located at:

```text
database/database schema.sql
```

The main tables used by the current workflow are:

| Table | Purpose |
|---|---|
| `accountdetails` | User account and contact details |
| `items` | Lost and found reports, including report type and status |
| `item_matches` | Lost/found item pairs, score, responses, and match status |
| `handover` | Meeting arrangement, PIN, handover status, and return timestamp |

The handover table is linked to `item_matches` by `match_id`. Check the SQL file for the authoritative column definitions.

## Project Structure

The repository currently uses a `src/back2u/` package structure. Key source files visible in the project include:

```text
back-2-u/
├── back2u.example.properties
├── database/
│   └── database schema.sql
├── src/
│   └── back2u/
│       ├── AdminDashboard.java
│       ├── AdminReport.java
│       ├── AllMatchesTest.java
│       ├── Conn.java
│       ├── CreateAccount.java
│       ├── DetailsMatch.java
│       ├── Forgotpassword.java
│       ├── HandoverArrangement.java
│       ├── ItemMatcher.java
│       ├── ItemMatchingService.java
│       ├── Login.java
│       ├── MatchNotificationService.java
│       ├── Notifications.java
│       ├── PossibleMatches.java
│       ├── ReportFounditem.java
│       ├── ReportLostItem.java
│       ├── UserDashboard.java
│       ├── handover.java
│       └── matchfound.java
├── .gitignore
└── README.md
```

This is a list of key files, not necessarily every file in the repository.

## Setup and Running

### Prerequisites
- Java JDK compatible with the project source.
- MySQL Server.
- MySQL JDBC driver configured in the project.
- NetBeans or another Java IDE.

### Steps

1. Clone the repository:

   ```bash
   git clone https://github.com/archa-s-ar/back-2-u.git
   ```

2. Open the project in NetBeans.
3. Start MySQL Server.
4. Create/select the `back2u` database.
5. Run the SQL statements from `database/database schema.sql` to create the required tables.
6. Configure the database connection locally.
7. Ensure the MySQL JDBC driver is available to the project.
8. Run the application's main class from the IDE.

### Database configuration

The repository includes `back2u.example.properties` as an example configuration. Follow the comments in that file and the loading logic in `Conn.java` to configure your local connection. Do not put a real password in the example file.

## Security Notes

- Never commit real database credentials or personal user data.
- Keep the real local properties file excluded from Git.
- Use prepared statements for SQL queries.
- Passwords should be stored using a suitable password-hashing method, not as plain text.
- Verify that only the owner of a confirmed match can create its handover arrangement.
- Verify that only the authorized finder can submit the PIN to complete that handover.
- Do not expose the PIN unnecessarily in the finder-facing interface or logs.
- Update the handover, match, and item statuses consistently in a database transaction.

## Testing Checklist

Before considering the workflow complete, test:

- [ ] Create an account and sign in.
- [ ] Report a lost item.
- [ ] Report a found item.
- [ ] Confirm a possible match from both user accounts.
- [ ] Reject a match and confirm that it cannot proceed.
- [ ] Confirm that the owner can create an arrangement only for their own match.
- [ ] Confirm that duplicate arrangements are prevented.
- [ ] Confirm that the finder can view the correct arrangement for the selected match.
- [ ] Enter an incorrect PIN and verify that the handover remains pending.
- [ ] Enter the correct PIN and verify all related status updates.
- [ ] Confirm that an unrelated user cannot complete the handover.
- [ ] Test database connection failure and empty form fields.
- [ ] Check that real credentials are not tracked by Git.

## Future Scope

Possible future improvements include:
- Image-based similarity matching
- Improved text similarity
- Email or push notifications
- A mobile application
- QR-based item identification
- Campus-wide deployment
- Analytics for frequently lost items
- Stronger duplicate-claim and fraud detection

## Team

**Project:** Back2U — Smart Campus Lost & Found System

**Tagline:** *There’s Always a Way Back.*

**Contributors**
- Aadithya K
- Archa S
- Gitto George
- Sreedurga P

## Project Status

**Status: Completed**

Back2U is an academic Java Swing project focused on object-oriented programming, database connectivity, matching logic, notifications, and the lost-item handover workflow. Feature completion should be confirmed through testing of the current code.

---

## Why Back2U?

Losing something on campus shouldn't mean losing it forever.

**Back2U — There’s Always a Way Back.**
