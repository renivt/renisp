RENISP Project Information:

Software Language: Java

Software Stack: Spring Boot. Junit

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
1)	Sprint Boot Startup class: Com.renisp.Application is the core spring boot class. This class handles the json request and response and then passes the models to the injected userService interface. 
2)	Interface: UserService is defined as a interface. 2 possible service implementattions can be used as mentioned in 4.
3)	Models: The input and the output parameters
4)	Service Layer: There are 2 implemetations provided for the service layer. 
    a.	Static hashtable (HTServiceImpl) implementation: This is completely implemented and tested. This was a simple way to implement the requirement. However, this will not work in a multi JVM environment. So the psudecode for a DB based implementation is also added.
    b.	DB (DBServiceImpl) based implementation: DB solution will be a more complete solution.This is only partially implemented. 

Testing:
1)	Test Driven development was used with Junit. The Junit class is included.
2)	Final testing on cloud was done with a postman plugin in chrome. Attached is the postman collection and results used for testing.
    a.  Request Collection - Test reniboot cloud.postman_collection.json
    b.  Test Result Collection - Test reniboot cloud.postman_test_run.json
