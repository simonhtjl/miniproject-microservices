# miniproject-microservices

5 service :
-Admin Service (this to CRUD buku dan ruangan perpustakaan)
-Order Service (in endpoint borrow buku, there's a line code to call another service which is admin service to check stock of buku)
-Api Gateways (spring cloud gateway)
-Auth Service (spring security)
-Discovery Server (spring eureka)

make sure auth,gateways,dicovery server is running

this service using mySql,if there's any trouble with connection to dabatase please make sure the port is same,in this service using port 3306

script and postman collection already put in this repo

script name miniproject.sql
collection name MiniProject.postman_collection.json

spring version 2.7.18
Jdk 1.8
Java version 8

sorry there's no UI only postman,i couldnt catch up with the deadline

