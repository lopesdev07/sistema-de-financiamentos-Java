# sistema-de-financiamentos-Java

# Java financing system | v1.5 BETA - 2026-06-15

**Description:** Financing application developed in Java for practice on backend development. Implements login/register logic, alongside with different types of financings (2.0 VER Exclusive) that can be manipulated through add, exclude and edit methods (excluding and editing are 2.0 VER Exclusive) and viewed so the user can analyze his own financing. All data are manipulated and stored in SQL/MySQL utilizing JDBC.

## Changelog - v1.5 - 2026-06-15
### Upgrades and Fixes:
- Better system architecture and code organization
- Better register and login logic, with more validations and security measures
- More validations and exception handling
- More user-friendly console interface
- Project structure more organized and modularized, with better separation of concerns
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
1. Create a new database in MySQL with the name you prefer (e.g., projeto_financiamentos)
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

**setx DB_URL "jdbc:mysql://localhost:3306/projeto_financiamentos"
setx DB_USER "root"
setx DB_PASSWORD "your_password"**

**(LINUX / MAC)**

**export DB_URL="jdbc:mysql://localhost:3306/projeto_financiamentos"
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

1: Finish the 2.0 VERSION of the project, adding more types of financings and finishing exclude and edit methods

2: Total migration of the project to Spring Boot
