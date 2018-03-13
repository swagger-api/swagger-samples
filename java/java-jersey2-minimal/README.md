# Swagger Sample App

## Overview
This is a java project to build a stand-alone server which implements the OpenAPI Spec.  You can find out 
more about both the spec and the framework at http://swagger.io.

This sample is based on jersey2, and provides a minimal example of integration of swagger into a jersey2 based app, with resources to be scanned provided by `swagger-jaxrs2-servlet-initializer` (`ServletContainerInitializer` implementation) 

### To run (with Maven)
To run the server, run this task:

```
mvn package -Dlog4j.configuration=file:./conf/log4j.properties jetty:run
```

This will start Jetty embedded on port 8002.

### Testing the server
Once started, you can navigate to http://localhost:8002/sample/openapi.json to view the Swagger Resource Listing.
This tells you that the server is up and ready to demonstrate Swagger.
