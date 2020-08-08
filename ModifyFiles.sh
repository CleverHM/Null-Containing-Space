#!/bin/bash
isServer=`grep -r '"dev"' frontend/package.json`
#echo "$isServer"
if [ "$isServer" == "" ]; then
	echo "Start as SERVER"
	# package.json
	echo "frontend/package.json"
	sed -i '6s/.*/    "serve": "vue-cli-service serve --host client --port 81 --open",/g' frontend/package.json
	sed -i '6 i\    "dev": "webpack-dev-server --inline --progress --host 0.0.0.0 --disableHostCheck true --config build/webpack.dev.conf.js",' frontend/package.json
	# src/util/http-common.js
	echo "src/util/http-common.js"
	sed -i '5s/.*/    baseURL:"http:\/\/52.79.249.53:8080", /g' frontend/src/util/http-common.js

else
	echo "Start as LOCAL"
	# package.json
	echo "frontend/package.json"
	sed -i '6s/.*/    "serve": "vue-cli-service serve --host localhost --port 3000 --open",/g' frontend/package.json
	sed -i '6d' frontend/package.json
	# src/util/http-common.js
	echo "src/util/http-common.js"
	sed -i '5s/.*/    baseURL:"http:\/\/localhost:8080", /g' frontend/src/util/http-common.js

fi


