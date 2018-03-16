# Swagger Sample App

## Overview
This is a java project to build a stand-alone server which implements the OpenAPI Spec.  You can find out 
more about both the spec and the framework at http://swagger.io.

This sample demonstrate resolving OpenAPI definition out of JAX-RS annotated resources, as a different application
than the one serving the actual APIs

### To run (with Maven)
To run the server, run this task:

```
mvn package -Dlog4j.configuration=file:./conf/log4j.properties jetty:run
```

This will start Jetty embedded on port 8002.

### Testing the server
Once started, you can navigate to http://localhost:8002/openapi/openapi.json to view the Swagger Resource Listing.
This tells you that the server is up and ready to demonstrate Swagger.
