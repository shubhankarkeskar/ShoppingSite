# ShoppingSite

Requirements
IDE - Intellij
Java version - 8
MySQL version - 5.7.24
Spring framework - 4.3.18 or more
javax.servlet - 3.0.1 or more
mysql-connector - 8.0.14
jstl lib - 1.2
Spring security - 5.0.6
HikariCP - 2.7.8
slf4j-api - 1.7.25
log4j - 1.2.17
slf4j-simple - 1.6.4

These are the basic requirements(dependencies) which are present except MySQL. It may be a different version on your system.
Project Created using maven.

Instructions
1. Import the project in our IDE
2. Create a database 'ShoppingSite'
3. Change the db user & password according to your config.
4. Run the sql scripts provided in SQL Folder
5. Run/Debug Configurations(if needed) - 
    URL - http://localhost:8080/ShoppingSite
    JRE - 1.8
    Tomcat - 9.0.14 (or you can specify your existing tomcat version suitable for the project)
    Create war if not present(war exploded) In ApplicationContext: /ShoppingSite
    Apply Changes
5. Build & run the project
6. For logs you can specify a different file path in log4j.properties
