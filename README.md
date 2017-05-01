# RENISP Project Information:

## Software Language
Java

## Software Stack
Spring Boot Microservice, Jackson, Junit

## Code Repository
https://github.com/renivt/renisp.git
This is a maven project. After importing as a maven project into a workspace, the maven install command can be used to compile, run the test case and create the jar.
The jar is then pushed to the cloud foundry through the cf command (cf push <appname>)

## Accessing the PCF cloud version
URL https://reniboot.cfapps.io. 

### Hosted API’s:
*	https://reniboot.cfapps.io/createFriends
*	https://reniboot.cfapps.io/retrieveFriends
*  https://reniboot.cfapps.io/retrieveCommonFriends
*  https://reniboot.cfapps.io/subscribe
*  https://reniboot.cfapps.io/block
*  https://reniboot.cfapps.io/validEmails

## Design:
This solution is implemented as a Rest Microservice and hosted on the PCF cloud.
###	Sprint Boot Startup class: 
com.renisp.Application is the core spring boot class that makes this solution a microservice.
###  Controller: 
The rest controller is implemented with spring annotation. For simplicity the controller is merged with the Spring Boot class. The controller This class handles the json request and response and then passes the models to the injected userService interface.  
###	Interface: 
UserService is defined as a interface. 2 possible service implementattions can be used as mentioned in 4.
###	Models: 
This package has the data transfer objects that map the json input and the output parameters through the controller and service/data access layer.
###	Service Layer: 
There are 2 implemetations provided for the service layer. 
*	Service Layer - Static hashtable (HTServiceImpl) implementation: This is completely implemented and tested. This was a simple way to implement the requirement. However, this will not work in a multi JVM environment. For simplicity, the service and data access layer is also clubbed together.
*	Service Layer - DB (DBServiceImpl) based implementation: DB solution will be a more complete solution that will work on a distributed environment.This is only partially implemented. There is a good segregation between the service and the data access layer.

## Testing:
1)	JUnit : Test Driven development was used with Junit. The Junit class is included.
2)	Postman : Final testing on cloud was done with a postman plugin in chrome. Attached is the postman collection and results used for testing.
3) 	Postman - Request Collection: Test reniboot cloud.postman_collection.json
4)	Postman - Test Result Collection: Test reniboot cloud.postman_test_run.json
