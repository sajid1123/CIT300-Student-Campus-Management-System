# University Student Record and Campus Route Management System

## 1. Project Overview

The University Student Record and Campus Route Management System is a Java-based console application developed for the CIT300 Data Structures and Algorithms Graded Practical Assignment 1.

The system is designed to manage university student records and campus locations while demonstrating the practical implementation of different data structures and algorithms.

The project uses Linked Lists, Stacks, Queues, Binary Search Trees, Hashing, Graphs, and Breadth-First Search (BFS).

The application provides a menu-driven console interface with 16 options for managing student records, student service requests, recent actions, student searching, and campus routes.

---

## 2. Technologies Used

- Java
- Git
- GitHub
- Visual Studio Code
- Java Console Interface

---

## 3. Data Structures and Algorithms

The following data structures and algorithms are implemented in the project:

### Singly Linked List
Used to store and manage student records.

### Stack
Used to store and display recent system actions using the Last In First Out (LIFO) principle.

### Queue
Used to manage student service requests using the First In First Out (FIFO) principle.

### Binary Search Tree (BST)
Used to organize student records by Student ID and display the records in sorted order.

### Hash Table
Used to provide efficient searching of student records using Student ID.

### Graph
Used to represent campus locations and the connections between those locations using an adjacency list.

### Breadth-First Search (BFS)
Used to traverse connected campus locations.

---

## 4. Main System Features

The system provides the following features:

- Add student records
- Update student records
- Delete student records
- Search student records
- Display student records using a Linked List
- Prevent duplicate Student IDs
- Validate student marks
- Add student service requests to a Queue
- Process student service requests using FIFO
- Display recent actions using a Stack
- Display students using BST traversal
- Search students using Hashing
- Add campus locations
- Remove campus locations
- Add campus connections or roads
- Remove campus connections or roads
- Display campus connections
- Traverse campus locations using BFS
- Handle missing student records
- Handle duplicate records
- Handle invalid marks
- Handle invalid or unavailable campus connections
- Handle invalid menu selections

The completed application contains all 16 menu options required for the system.

---

## 5. System Menu

The application provides the following menu options:

1. Add Student Record
2. Update Student Record
3. Delete Student Record
4. Display All Records using Linked List
5. Add Service Request to Queue
6. Process Next Service Request
7. Display Recent Actions using Stack
8. Display Students using BST
9. Search Student using Hashing
10. Add Campus Location
11. Remove Campus Location
12. Add Campus Connection/Road
13. Remove Campus Connection/Road
14. Display Campus Connections
15. Traverse Campus Locations using BFS
16. Exit

---

## 6. Group Members and Responsibilities

| Member | Student ID | Main Responsibility |
|---|---|---|
| M.S.M Sajid | 23DA2-1090 | Linked List and Student Record Management |
| A.N. Fathima Sipani | 23DA2-1101 | Stack and Queue Implementation |
| A.J. Jasan | 23DA2-1082 | BST and Hashing |
| A.R. Jasana | 23DA2-1081 | Graph and BFS |

All members also participated in project integration, validation, testing, debugging, documentation, GitHub collaboration, and the final project demonstration.

---

## 7. Individual Contributions

### Member 1 – M.S.M Sajid (23DA2-1090)

- Implemented the Linked List for student record management.
- Implemented student record operations including adding, updating, deleting, searching, and displaying student records.
- Implemented duplicate Student ID validation.
- Acted as the group leader.
- Coordinated the final GitHub integration.
- Integrated `Main.java` and `StudentManagementSystem.java` into the completed system.
- Participated in validation, testing, debugging, documentation, and final project integration.
- Demonstrated the Linked List and Student Record Management component in the final video.

### Member 2 – A.N. Fathima Sipani (23DA2-1101)

- Implemented the Queue for student service requests.
- Implemented FIFO processing of student service requests.
- Implemented the Stack for storing and displaying recent system actions.
- Participated in integration, validation, testing, debugging, documentation, and GitHub collaboration.
- Demonstrated the Stack and Queue component in the final video.

### Member 3 – A.J. Jasan (23DA2-1082)

- Implemented the Binary Search Tree for organizing student records using Student ID.
- Implemented BST traversal to display Student IDs in sorted order.
- Implemented Hashing for Student ID searching.
- Implemented handling for existing and missing Student IDs.
- Participated in integration, validation, testing, debugging, documentation, and GitHub collaboration.
- Demonstrated the BST and Hashing component in the final video.

### Member 4 – A.R. Jasana (23DA2-1081)

- Implemented the campus Graph using an adjacency list.
- Implemented campus location management.
- Implemented adding and removing campus locations.
- Implemented adding and removing campus connections or roads.
- Implemented Breadth-First Search (BFS) for campus traversal.
- Participated in integration, validation, testing, debugging, documentation, and GitHub collaboration.
- Demonstrated the Graph and BFS component in the final video.
- Demonstrated GitHub collaboration including branches, commits, pull requests, and the final main branch.

---

## 8. Validation and Error Handling

The system includes input validation and error handling for different situations.

These include:

- Invalid student marks
- Duplicate Student IDs
- Missing student records
- Invalid user inputs
- Duplicate campus locations
- Missing campus locations
- Invalid campus connections
- Unavailable campus connections
- Empty service requests
- Invalid menu selections

The integrated application was tested to confirm that the required operations and data structures work correctly.

---

## 9. GitHub Collaboration

GitHub was used for collaborative development and version control throughout the project.

Each member worked on their assigned component using separate feature branches.

Individual commits were created for the different components of the system.

Pull requests were used to review and merge the individual components into the main branch.

The group leader also used a separate system integration branch to integrate the final application.

The final `main` branch contains the complete integrated project.

GitHub collaboration provides evidence of:

- Individual branches
- Individual commits
- Pull requests
- Component integration
- Final system integration
- Collaborative project development

---

## 10. Project Structure

```text
CIT300-Student-Campus-Management-System/
│
├── src/
│   ├── ActionStack.java
│   ├── CampusGraph.java
│   ├── Main.java
│   ├── ServiceQueue.java
│   ├── Student.java
│   ├── StudentBST.java
│   ├── StudentHashTable.java
│   ├── StudentLinkedList.java
│   └── StudentManagementSystem.java
│
├── .gitignore
└── README.md
