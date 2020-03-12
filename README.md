# Book Store
Book store web service responsible for managing and ordering books online 

To implement Rest API, following have been used : 
1. Spring boot
2. Spring-data-jpa
3. Mysql database for storing and managing the RD
4. Swagger.io for API documentation

![](images/erdiagram.png)

# STEPS TO INSTALL

Prerequisite :
1. JAVA 8
2. Mysql Server

Steps to install : 

1. update application.properties with the mysql database url and credentials.
2. In mysql create database schema using command : 

```
CREATE DATABASE bookstore
mysql -u username -p password bookstore < {project-base-location}/sqldump/bookstoreSchemaDump.sql
```
Alternatively, use Mysql Workbench for the same.
3. Build the project using maven command :
`mvn clean intall`

4. Run the application using java command : 

````
java -jar target/book-store-0.0.1-SNAPSHOT.jar --server.port=PORT --logging.file.path=FolderPathWhereTheLogFileIsCreated
````
For example
````
java -jar target/book-store-0.0.1-SNAPSHOT.jar --server.port=8086 --logging.file.path=C:/Users/himaniagarwal/Documents/logs
````
5. One the application is up and running ,  the documentation for the RestAPI methods can be availed here : 
http://localhost:8080/swagger-ui.html
