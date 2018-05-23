# Swagger Petstore Sample

## Overview
This is a java project to build a stand-alone server which implements the OpenAPI 3 Spec.  You can find out
more about both the spec and the framework at http://swagger.io.

This sample is based on [swagger-inflector](https://github.com/swagger-api/swagger-inflector), and provides an example of swagger / OpenAPI 3 petstore.

### To run (with Maven)
To run the server, run this task:

```
mvn package jetty:run
```

This will start Jetty embedded on port 8080.

### To run (via Docker)

Expose port 8080 from the image and access petstore via the exposed port. You can then add and delete pets as you see fit.


*Example*:

```
docker build -t swaggerapi/petstore3:unstable .
```

```
docker pull swaggerapi/petstore3:unstable
docker run  --name swaggerapi-petstore3 -d -p 8080:8080 swaggerapi/petstore3:unstable
```


### Testing the server
Once started, you can navigate to http://localhost:8080/api/v3/openapi.json to view the Swagger Resource Listing.
This tells you that the server is up and ready to demonstrate Swagger.

### Using the UI
There is an HTML5-based API tool bundled in this sample--you can view it it at [http://localhost:8080](http://localhost:8080). This lets you inspect the API using an interactive UI.  You can access the source of this code from [here](https://github.com/swagger-api/swagger-ui)
