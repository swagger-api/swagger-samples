# Swagger, Jersey, Governator Sample App

This app shows you how to combine Swagger, Jersey 1.X, and [Governator](https://github.com/Netflix/governator)

### To run (with Maven)
To run the server, run this task:

```
mvn package jetty:run-war
```

This will start Jetty embedded on port 8002.

### Testing the server
Once started, you can navigate to http://localhost:8002/swagger.json to view the Swagger Resource Listing.
This tells you that the server is up and ready to demonstrate Swagger.


### Using the UI
There is an HTML5-based API tool bundled in this sample--you can view it it at [http://localhost:8002/docs](http://localhost:8002/docs). This lets you inspect the API using an interactive UI.  You can access the source of this code from [here](https://github.com/swagger-api/swagger-ui)

### Using the Karyon 2 Admin Page

You can view the Karyon 2 Admin Page at [http://localhost:8077/adminres](http://localhost:8077/adminres). This page gives you access to debug/admin features provided by karyon.

