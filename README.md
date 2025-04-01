# **Hustle Hub: The Authentic Scheduler**  

![Hustle Hub Banner](/images/hustleHub.png) *(Replace with actual project banner)*  

**Hustle Hub** is a comprehensive **task management system** designed to help users efficiently manage their personal and professional tasks. It allows users to **create, organize, and track tasks** by setting priorities, due dates, and categories. With features like **user authentication, task categorization, status tracking, and in-app notifications**, Hustle Hub ensures a seamless experience for individuals and teams to stay **organized and productive**.  

---

## **📌 Table of Contents**  
1. [Features](#-features)  
2. [Technologies Used](#-technologies-used)  
3. [Installation & Setup](#-installation--setup)  
4. [Folder Structure](#-folder-structure)  
5. [Screenshots](#-screenshots)  
6. [Data Modeling](#-data-modeling)  
7. [API Documentation](#-api-documentation)  
8. [Contributing](#-contributing)  
9. [License](#-license)  
10. [Contact](#-contact)  

---

## **✨ Features**  
✅ **User Authentication** – Secure login/signup using JWT.  
✅ **Task Management** – Create, edit, delete, and categorize tasks.  
✅ **Priority & Due Dates** – Set task priorities (*High, Medium, Low*) and deadlines.  
✅ **Status Tracking** – Mark tasks as *Pending, In Progress, or Completed*.  
✅ **Notifications** – In-app alerts for upcoming deadlines.  
✅ **Responsive UI** – Works smoothly on desktop and mobile.  

---

## **🛠 Technologies Used**  

### **Frontend**  
- **Angular** – UI Framework  
- **NgRx** – State Management  
- **HttpClientModule** – HTTP Requests  
- **Angular Material / NG Bootstrap** – Styling & UI Components  

### **Backend**  
- **Spring Boot** – RESTful API  
- **Spring Security** – Authentication & Authorization  
- **Spring Data JPA** – Database Interaction  
- **PostgreSQL** – Database  

### **Other Tools**  
- **JWT** – Authentication Tokens  
- **Git** – Version Control  

---

## **⚙ Installation & Setup**  

### **Prerequisites**  
- **Node.js & npm** (for Angular)  
- **Java JDK 11+** (for Spring Boot)  
- **PostgreSQL** (Database)  

### **Steps to Run**  

#### **1. Backend (Spring Boot)**  
```bash
cd server  
mvn spring-boot:run  # Starts the backend on http://localhost:8080
```

#### **2. Frontend (Angular)**  
```bash
cd client  
npm install          # Install dependencies  
ng serve             # Runs the frontend on http://localhost:4200  
```

---

## **📂 Folder Structure**  

```bash
Hustle-Hub/  
│  
├── client/            # Frontend (Angular)  
│   ├── src/  
│   │   ├── app/       # Components, Services, NgRx Store  
│   │   ├── assets/    # Images, Styles  
│   │   └── ...  
│  
├── server/            # Backend (Spring Boot)  
│   ├── src/main/java/  
│   │   ├── controller/  
│   │   ├── model/  
│   │   ├── repository/  
│   │   ├── service/  
│   │   └── ...  
│  
└── data-modelling/    # Database Schema & ER Diagrams  
    └── data_model.md  # Data modeling structure
    └── images/   
```

---

## **📸 Screenshots**  

### **Login Page**
![Login Page](/images/loginPage.png)  
### **Task Dashboard**  
![Task Dashboard](/images/taskDashboard.png)  
### **Task Creation Modal**  
![Task Creation Modal](/images/newTask.png)  
### **Profile Dashboard**  
![Profile Sadhboard](/images/profileDashboard.png)  

---

## **🗄 Data Modeling**  
### **ER Diagram**  

*(Insert ER Diagram here)*  
![ER Diagram](/images/er-diagram.png)  

---

## **📜 API Documentation**  

### **Base URL**  
`[http://localhost:8080/](http://localhost:8080/)`  

### **Endpoints**  

#### **Authentication**  
| Method | Endpoint          | Description                |
|--------|-------------------|----------------------------|
| `POST` | `/api/user/create`    | Register a new user        |
| `POST` | `/api/user/apiLogin`     | Login with credentials     |

#### **Tasks**  
| Method | Endpoint          | Description                    |
|--------|-------------------|--------------------------------|
| `GET`  | `/api/task/all/{categoryId}`          | Get all tasks (requires auth)  |
| `POST` | `/api/task/create/{categoryId}`          | Create a new task              |
| `PUT`  | `/api/task/update/{categoryId}/{taskId}`     | Update a task                  |
| `DELETE`| `/api/task/delete/{categoryId}/{taskId}`     | Delete a task                  |

### **Sample Request (Login)**  
```json
POST /auth/login
{
  "email": "user@example.com",
  "password": "yourpassword"
}
```

### **Sample Response (Get Tasks)**  
```json
GET /tasks
{
  "message": "string",
  "result": true,
  "data": [
    {}
  ]
}
```

### **Swagger UI**  
`Access API docs interactively at http://localhost:8080/swagger-ui.html`  

---

## **🤝 Contributing**  

1. **Fork** the repository.  
2. **Create a branch** (`git checkout -b feature/your-feature`).  
3. **Commit changes** (`git commit -m "Add your feature"`).  
4. **Push to the branch** (`git push origin feature/your-feature`).  
5. **Open a Pull Request**.  

---

## **📧 Contact**  

- **Email**: your.email@example.com  
- **GitHub**: [@jasjeev013](https://github.com/jasjeev013)  
- **LinkedIn**: [Jasjeev Singh Kohli](https://www.linkedin.com/in/jasjeev-singh-k-773238247)  

*(Replace placeholders with actual links/data. Remove sample APIs if not applicable.)*  



