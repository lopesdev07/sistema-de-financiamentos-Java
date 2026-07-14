# sistema-de-financiamentos-Java

# Java financing system | v1.8.5 BETA - 2026-07-14

**Description:** Financing application developed in Java for practice on backend development. Implements login/register system, alongside with different types of financings (2.0 VER Exclusive) that can be manipulated through add and edit methods and viewed so the user can analyze his own financing. All data are manipulated and stored in SQL/MySQL utilizing JDBC.

## Changelog - v1.8.5 - 2026-07-14
### Upgrades and Fixes:
- All classes, enums, methods, texts, messages, comments and getters/setters were translated to english.

- A bug that inverted the logic of CPF validation was fixed.

- Unreachable code after a throw statement was removed, and two minor code redundancies were cleaned up (an empty conditional block and an unused constructor parameter).

- Boolean loop control in MainMenuView had its logic and name changed for better implementation

- Database entirely translated to english

---

## Utilized Technologies
- Java 24
- IDE: IntelliJ
- Data structure: Data persistence with MySQL and JDBC

---

## Project Structure
**ProjetoFinanciamentos/**

**database** → schema.sql

**java** → exceptions, model, repository, service, util, view, Main.java.

---

## Data Bank

This project utilizes **MySQL** for data permanency.

Data bank structure is defined in the file

database/schema.sql

### How to set up the Database
1. Create a new database in MySQL with the name you prefer (e.g., financing_project)
2. Execute the following command in your terminal, replacing `your_user`, `database_name` and `schema.sql` with your MySQL username, the name of the database you created and the path to the schema.sql file, respectively:
```bash
mysql -u your_user -p database_name < database/schema.sql
```

##  How to execute the project
*-- Important --*
**To avoid versioning sensitive information, environment variables are used.**

Before running the project, it's **obligatory** to configurate the following environment variables in your system:

- `DB_URL`
- `DB_USER`
- `DB_PASSWORD`
- 
    *THE RESTART OF THE IDE OR THE TERMINAL IS OBLIGATORY FOR THE NECESSARY CHANGES*

**HOW TO DO THIS FROM POWERSHELL/CMD (WINDOWS)**

**setx DB_URL "jdbc:mysql://localhost:3306/financing_project"
setx DB_USER "root"
setx DB_PASSWORD "your_password"**

**(LINUX / MAC)**

**export DB_URL="jdbc:mysql://localhost:3306/financing_project"
export DB_USER="root"
export DB_PASSWORD="your_password"**

1. **Clone this repository**
   ```bash
   git clone https://github.com/lopesdev07/sistema-de-financiamentos-Java
   ```
2. **Open the project**
   Open the project in your IDE (e.g., IntelliJ)
3. **Compile and run**
   Localize on the IDE the file Main.java and hit **Run**

---

**Next steps for the project:**

1: Finish the 2.0 VERSION of the project, this includes:
- Adding more types of financings
- Create methods for scanners and loops
- Implementate a proper logging framework for better debugging and error tracking

2: Total migration of the project to Spring Boot
