# Software Engineering Process: Invoice Model Design (Part A)

## 💡 Overview
This project, **Part A: Invoice**, is the first assignment in a series dedicated to practicing the complete Software Engineering design process. The objective is to analyze requirements for an invoice application and translate those requirements into a robust Object-Oriented design.

The core functionality models an invoice composed of multiple `LineItem` objects, where each line item calculates its subtotal based on a specific `Product` and quantity.

## 🎯 Design Process & Deliverables
This lab demonstrates the following stages of the software engineering process:

1.  **Requirements Analysis:** Deriving core business needs from a task description.
2.  **Class Derivation (Candidate List):** Identifying necessary classes from the requirements documents.
3.  **CRC Card Development:** Defining the **Responsibilities** and **Collaborators** for the core domain classes.
4.  **UML Modeling:** Creating a comprehensive blueprint of the class relationships (composition, association).

## 🛠️ Domain Model Implementation

### Core Classes Identified
The final design models the Invoice application using these four classes:
* `Product`
* `LineItem`
* `Invoice`
* `InvoiceGUI`

### Responsibility/Collaboration (CRC) Summary
| Class | Core Responsibility | Key Collaborators |
| :--- | :--- | :--- |
| **Product** | Know its name and unit price. | `LineItem` (references the Product) |
| **LineItem** | Calculate line subtotal (price * quantity). | `Product`, `Invoice` |
| **Invoice** | Manage the list of `LineItem`s; calculate the grand total. | `LineItem`, `InvoiceGUI` |
| **InvoiceGUI** | Manage user interface (display, input fields, buttons). | `Product`, `LineItem`, `Invoice` |

## 📐 UML Class Diagram
The final design is represented by a UML Class Diagram, which serves as the blueprint for the subsequent implementation phase. The diagram illustrates how the `Invoice` class aggregates `LineItem`s, which in turn hold a reference to a `Product`. The `InvoiceGUI` acts as the primary interface, interacting with all three domain classes. 
<img width="595" height="824" alt="lab7a UML" src="https://github.com/user-attachments/assets/9268ce71-19bf-4840-9107-b8e0a5947d71" />

## 🧪 Implementation Note
The final coding stage (Part B) will involve implementing these classes and creating **JUnit tests** using an **Agile test-first approach**, focusing initially on stubbing out methods to validate the design.
