# QuizApp

A **Java console-based quiz application** where the user answers multiple-choice questions, gets scored, and can review results.  
The quiz shuffles both questions and options each time, making each attempt unique.

---

## 📌 Features
- **Multiple-choice questions** (4 options each)
- **Shuffles** questions and options on every run
- **User input validation** – only accepts A–D
- **Score calculation** with percentage and performance remarks
- **Answer review** after the quiz with correct/incorrect indicators
- **Option to retake** the quiz without restarting
- Java **8+ compatible** (uses `Arrays.asList()`)

---

## 📂 Project Structure
QuizApp/
│
├── Question.java # Model class for storing question details
├── Quiz.java # Handles quiz logic, scoring, and review
└── QuizApp.java # Main entry point

---

## 🛠 Requirements
- Java 8 or higher
- Command prompt / terminal

---

## ▶️ How to Run

### 1️⃣ Clone the repository
```bash
git clone https://github.com/your-username/QuizApp.git
cd QuizApp
