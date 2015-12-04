# Swagger Playframework Sample App

## Overview
This is a scala project to build a stand-alone server which implements the Swagger spec.  You can find out 
more about both the spec and the framework at http://swagger.io.  For more information 
about Wordnik's APIs, please visit http://developer.wordnik.com.  There is an online version of this
server at http://petstore.swagger.wordnik.com/api/api-docs.json

## Version compatibility
=======
This version is compatible with Play 2.4.0 and Swagger 1.5.0-SNAPSHOT

### To build Swagger from source (optional)
Please follow instructions to build the top-level [swagger-core project](https://github.com/swagger-api/swagger-play)

### To run
The swagger-play2 module lives in maven central:

```scala
val appDependencies: Seq[sbt.ModuleID] = Seq(
  /* your other dependencies */
  "io.swagger" %% "swagger-play2" % "1.5.0-SNAPSHOT"
)
```

You can run the sample app as such:

````
activator testProd
````

The application will listen on port 9000 and respond to `http://localhost:9000/api-docs`

You can test the sample app as such:


````
activator test
````