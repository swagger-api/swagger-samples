FROM java:8-jdk

WORKDIR /petstore
COPY ./jetty-runner* /petstore/jetty-runner.jar
COPY ./run.sh /petstore/
ADD target/swagger-java-jersey-sample-app-1.0.0 /petstore/webapp

RUN apt-get update

EXPOSE 8080
CMD ["bash", "/petstore/run.sh"]
