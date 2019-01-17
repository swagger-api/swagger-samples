#!/bin/sh

set -e

# use default URL if none supplied
if [ -z $SWAGGER_URL ]; then SWAGGER_URL=http://localhost:8080; fi

# make it safe for sed
URL=$(sed -e 's,/,\\/,g' <<< $SWAGGER_URL)
sed -i "s/SWAGGER_HOST/$URL/g" /petstore/webapp/WEB-INF/web.xml
sed -i "s/SWAGGER_HOST/$URL/g" /petstore/webapp/index.html

# use default basePath if none supplied
if [ -z $SWAGGER_BASE_PATH ]; then SWAGGER_BASE_PATH=/api; fi

# make it safe for sed
BASE_PATH=$(sed -e 's,/,\\/,g' <<< $SWAGGER_BASE_PATH)
sed -i "s/BASE_PATH/$BASE_PATH/g" /petstore/webapp/WEB-INF/web.xml

# full path
FULL_PATH=$SWAGGER_URL$SWAGGER_BASE_PATH
FULL_SWAGGER_PATH=$(sed -e 's,/,\\/,g' <<< $FULL_PATH)
sed -i "s/FULL_SWAGGER_PATH/$FULL_SWAGGER_PATH/g" /petstore/webapp/index.html
sed -i "s/FULL_SWAGGER_PATH/$FULL_SWAGGER_PATH/g" /petstore/webapp/WEB-INF/web.xml

java -jar /petstore/jetty-runner.jar /petstore/webapp
