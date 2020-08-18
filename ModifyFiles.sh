#!/bin/bash
isServer=`grep -r '"dev"' frontend/package.json`
#echo "$isServer"
if [ "$isServer" == "" ]; then
	echo "Start as SERVER"
	# package.json
	echo "frontend/package.json"
	sed -i '6s/.*/    "serve": "vue-cli-service serve --host client --port 80 --open",/g' frontend/package.json
	sed -i '6 i\    "dev": "webpack-dev-server --inline --progress --host 0.0.0.0 --disableHostCheck true --config build/webpack.dev.conf.js",' frontend/package.json
	sed -i '8 i\    "build":"vue-cli-service build",' frontend/package.json
	# src/util/http-common.js
	echo "src/util/http-common.js"
	sed -i '5s/.*/    baseURL:"http:\/\/52.79.249.53:8080", /g' frontend/src/util/http-common.js

	# /SNS_Backend/src/main/resources/application.properties
	sed -i 's/#spring.datasource.url=jdbc:mariadb:\/\/mariadb/spring.datasource.url=jdbc:mariadb:\/\/mariadb/g' SNS_Backend/src/main/resources/application.properties
	sed -i 's/spring.datasource.url=jdbc:mariadb:\/\/localhost/#spring.datasource.url=jdbc:mariadb:\/\/localhost/g' SNS_Backend/src/main/resources/application.properties

else
	echo "Start as LOCAL"
	# package.json
	echo "frontend/package.json"
	sed -i '6d' frontend/package.json
	sed -i '7d' frontend/package.json
	sed -i '6s/.*/    "serve": "vue-cli-service serve --host localhost --port 3000 --open",/g' frontend/package.json
	# src/util/http-common.js
	echo "src/util/http-common.js"
	sed -i '5s/.*/    baseURL:"http:\/\/localhost:8080", /g' frontend/src/util/http-common.js
	# /SNS_Backend/src/main/resources/application.properties
	sed -i 's/spring.datasource.url=jdbc:mariadb:\/\/mariadb/#spring.datasource.url=jdbc:mariadb:\/\/mariadb/g' SNS_Backend/src/main/resources/application.properties
	sed -i 's/#spring.datasource.url=jdbc:mariadb:\/\/localhost/spring.datasource.url=jdbc:mariadb:\/\/localhost/g' SNS_Backend/src/main/resources/application.properties

fi


