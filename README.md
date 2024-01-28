
This is Library Management system

In this Project, i have implementated with the below

  1. Spring Boot 3.2.2 version
  2. Java version 17
  3. Basic Authentication with spring security ( when sending requesting from postman or swagger, please include username: library and password: library in the Authorization and type as Basic Auth ) 
  4. Spring Cache
  5. Swagger
  6. Aspects
  7. H2 as In-Memory Database
  8. Field Validation and Error Handling
  9. Unit Testing & Rest Controller Test case
  10. RestController Advice
     
below are the prerequisite Java 17 Apache Maven Lombok plugin should be installed in eclipse (or) Enable Lombok Annotation while starting up the service in Intellji

Once you have pull the source code from github

please do maven update and mvn clean install

Library Management System:

H2 Database URL: http://localhost:8080/api/h2-console

Database name: librarydb
username: sa
password: password

Swagger:

http://localhost:8080/api/swagger-ui/index.html

Book Api details:
================
Description: Getting All Books Record
URL : http://localhost:8080/api/books
Method : GET
success: 200 as status code (Success) and response: List of Book Records or Empty records
=============
Description: Getting One Book Record
URL : http://localhost:8080/api/books/{id} 
Method : GET
success: 200 as status code (Success) and response: Book object for the specified id
error: 404 as status code (Not Found) and response:  "Book for the id: 1. not found"
==============
Description: Inserting Book record
URL :  http://localhost:8080/api/books 
Method : POST 
success: 201 as status code (Created) 
Bad Request: 400 if the Book,Patron and IBSN is already exist 
& Validation error: 400 as status code (Bad Request) 
content-type: application/json
Request Body (JSON):
{
    "title":"Science1 Book",
    "author":"Testin1g1",
    "publicationYear":"2018-03-21",
    "ISBN":"12131"
}
===============
Description: updating Book record
URL :  http://localhost:8080/api/books/1
Method : Put 
success: 200 as status code (OK) 
Not Found: if the specified id is not available
Bad Request: 400 if the Book,Author and IBSN is already exist
& Validation error: 400 as status code (Bad Request) 
content-type: application/json
Request Body (JSON):
{
    "title":"Science Books",
    "author":"Wahid",
    "publicationYear":"2018-03-29",
    "ISBN":"1222"
}
============
Description: Deleting an Book record
URL :  http://localhost:8080/api/books/1
Method : Delete
success: 204 as status code (No Content) 
Not Found (404): if the specified id is not available
===============
Patron Api Details

Description: Getting All Patrons Record
URL : http://localhost:8080/api/patrons
Method : GET
success: 200 as status code (Success) and response: List of Patrons Records or Empty records
=====
Description: Getting One Patrons Record
URL : http://localhost:8080/api/patrons/{id} 
Method : GET
success: 200 as status code (Success) and response: Patron object for the specified id
error: 404 as status code (Not Found) and response:  "Patrons for the id: 1. not found"
======================
Description: Inserting Patrons record
URL :  http://localhost:8080/api/patrons
Method : POST 
success: 201 as status code (Created) 
Bad Request: 400 if the name,contact is already exist 
& Validation error: 400 as status code (Bad Request) 
content-type: application/json
Request Body (JSON):
{
    "name": "XYZ",
    "contactInformation": "xyz"
}
==============
Description: updating Patron record
URL :  http://localhost:8080/api/patrons/1
Method : Put 
success: 200 as status code (OK) 
Not Found(404): if the specified id is not available
Bad Request: 400 if the name,contact is already exist 
& Validation error: 400 as status code (Bad Request) 
content-type: application/json
Request Body (JSON):
{
    "id": 1,
    "name": "XYZ",
    "contactInformation": "xyz"
}
============
Description: Deleting an Patron record
URL :  http://localhost:8080/api/patrons/1
Method : Delete
success: 204 as status code (No Content) 
Not Found (404): if the specified id is not available
====================
Borrow Record API Details

Description: Allow a patron to borrow a book.
METHOD: POST
URL: /api/borrow/{bookId}/patron/{patronId}
Request body (JSON):
{
    "bookId":1,
    "patronId":1,
    "borrowDate":"2018-03-29"
}
success: 201 as status code (Created) 
Error:
Not Found: if the specified Book id is not available
Not Found: if the specified Patron id is not available
==============
Description: Record the return of a borrowed book by a patron.
METHOD: PUT
URL: /api/return/{bookId}/patron/{patronId}
Request body (JSON):
{
    "borrowingId":1,
    "bookId":1,
    "patronId":1,
    "returnDate":"2018-03-29"
}
success: 200 as status code (OK) 
Error:
Not Found (404): if the specified Book id is not available
Not Found (404): if the specified Patron id is not available
Not Found (404): if the specified Borrowing id is not available
==================
