# Swagger Sample App

## Overview
This is a java project to build a stand-alone server which implements the OpenAPI Spec. You can find out 
more about both the spec and the framework at http://swagger.io.

This sample is based on CXF and Spring Boot, and provides a minimal example of integration of swagger into a CXF based app.

### To run (with Maven)
To run the server, run this task:

```
mvn spring-boot:run
```

This will start Tomcat embedded on port 8080.

### Testing the server
Once started, you can navigate to http://localhost:8080/services/openapi.json to view the Swagger Resource Listing.
This tells you that the server is up and ready to demonstrate Swagger.

Swagger-UI is reachable at: http://localhost:8080/services/api-docs?url=/services/openapi.yaml