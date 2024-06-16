# Billboard Manager 

## Requirements

- Node 16.
- Java 17 (tested with Eclipse Temurin).
- Maven 3.8+.
- MySQL 8.

## Database creation

```
Start Mysql server if not running (e.g. mysqld).

mysqladmin -u root create <name> -p
mysqladmin -u root create <testName> -p

mysql -u root -p
    CREATE USER '<name>'@'localhost' IDENTIFIED BY '<password>';
    GRANT ALL PRIVILEGES ON paproject.* to '<name>'@'localhost' WITH GRANT OPTION;
    GRANT ALL PRIVILEGES ON <testName>.* to '<name>'@'localhost' WITH GRANT OPTION;
    exit
```

## Run

```
cd backend
mvn sql:execute (only first time to create tables)
mvn spring-boot:run

cd frontend
npm install (only first time to download libraries)
npm start
```
