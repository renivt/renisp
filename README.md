RENISP Project Information:

Software Language: Java

Software Stack: Spring Boot Microservice, Jackson, Junit

Code Repository: https://github.com/renivt/renisp.git

Accessing the PCF cloud version: 
URL https://reniboot.cfapps.io. 

Hosted API’s:
1)	https://reniboot.cfapps.io/createFriends
2)	https://reniboot.cfapps.io/retrieveFriends
3)  https://reniboot.cfapps.io/retrieveCommonFriends
4)  https://reniboot.cfapps.io/subscribe
5)  https://reniboot.cfapps.io/block
6)  https://reniboot.cfapps.io/validEmails

Design:
This solution is implemented as a Rest Microservice and hosted on the PCF cloud.
1)	Sprint Boot Startup class: Com.renisp.Application is the core spring boot class. 
2)  Controller: The rest controller is implemented with spring annotation. For simplicity the controller is merged with the Spring Boot class. The controller This class handles the json request and response and then passes the models to the injected userService interface.  
2)	Interface: UserService is defined as a interface. 2 possible service implementattions can be used as mentioned in 4.
3)	Models: This package has the data transfer objects that map the json input and the output parameters through the controller and service/data access layer.
4)	Service Layer: There are 2 implemetations provided for the service layer. 
    a.	Static hashtable (HTServiceImpl) implementation: This is completely implemented and tested. This was a simple way to implement the requirement. However, this will not work in a multi JVM environment. For simplicity, the service and data access layer is also clubbed together.
    b.	DB (DBServiceImpl) based implementation: DB solution will be a more complete solution that will work on a distributed environment.This is only partially implemented. There is a good segregation between the service and the data access layer.

Testing:
1)	Test Driven development was used with Junit. The Junit class is included.
2)	Final testing on cloud was done with a postman plugin in chrome. Attached is the postman collection and results used for testing.
    a.	Request Collection - Test reniboot cloud.postman_collection.json
    b.	Test Result Collection - Test reniboot cloud.postman_test_run.json
