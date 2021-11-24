# Swagger Playframework Sample App

## Overview
This is a scala project to build a stand-alone server which implements the OpenAPI Spec.  You can find out 
more about both the spec and the framework at http://swagger.io.  There is an online version of this
server at [http://petstore.swagger.io](http://petstore.swagger.io)

## Version compatibility
=======
This version is compatible with Play 2.6 and Swagger 1.5.X


### To run
The swagger-play2 module lives in maven central:

```scala
val appDependencies: Seq[sbt.ModuleID] = Seq(
  /* your other dependencies */
  "io.swagger" %% "swagger-play2" % "1.6.1"
)
```

You can run the sample app as such:

```
sbt run
```
or
```
activator testProd
````

The application will listen on port 9000 and respond to `http://localhost:9000/swagger.json`

You can test the sample app as such:


````
activator test
````