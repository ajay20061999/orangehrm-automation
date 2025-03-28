# OrangeHRM Automation Framework

Welcome to the **OrangeHRM Automation Framework** built using **Java**, **Playwright**, and **TestNG**. This project follows the **Page Object Model (POM)** design pattern for clean and scalable test automation.

---

## 🚀 Tech Stack

| Tool        | Purpose                          |
|-------------|----------------------------------|
| Java        | Programming Language             |
| Maven       | Build and Dependency Management  |
| TestNG      | Testing Framework                |
| Playwright  | Browser Automation               |
| GitHub      | Version Control                  |

---

## 📁 Project Structure

```
orangehrm-automation/
├── pom.xml                     # Maven dependencies
├── testng.xml                  # TestNG suite config
├── README.md                   # Project documentation
├── src
│   ├── main
│   │   └── java
│   │       └── com.orangehrm
│   │           ├── base        # Base setup
│   │           ├── pages       # Page classes (POM)
│   │           └── utils       # Utility classes
│   └── test
│       ├── java
│       │   └── com.orangehrm.tests # Test classes
│       └── resources           # config.properties
```

---

## ▶️ How to Run the Tests

1. Install Playwright Browsers:
   ```bash
   npx playwright install
   ```

2. Run tests with Maven:
   ```bash
   mvn clean test
   ```

3. Browser will launch, login to OrangeHRM, and assert successful login.

---

## 🔐 Config File (src/test/resources/config.properties)
```properties
baseUrl=https://opensource-demo.orangehrmlive.com/
username=Admin
password=admin123
```

---

## ✅ Sample Test Scenario: Login Test
- Navigate to OrangeHRM login page
- Enter username and password from config
- Click login
- Assert that dashboard is displayed

---

## 📸 Screenshots & Reporting *(Coming Soon)*
- ExtentReports setup (beautiful HTML reports)
- GitHub Actions CI integration

---

## 🙌 Author
**Ajanthan Muraleetharan** (Managed by Ajanthan Muraleetharan)

---

## 💬 Contributions & Issues
Feel free to fork this repo, open issues, or suggest improvements!

---

## 📜 License
This project is for educational/demo purposes. Feel free to modify and reuse.