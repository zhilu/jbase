#!/bin/bash
PID_FILE='pid'
# start location
CURRENT_DIR=`pwd`

# get */bin direction
BIN_DIR='dirname $0'

# from bin directory to parent directory get project directory
cd $BIN_DIR
cd ..

# get project directory
PROJECT_DIR=`pwd`


PROJECT_NAME=./fbase-0.0.1.jar
JAVA_OPTS="-server -Xms4G -Xmx8G -Xss128k -XX:PermSize=64M -XX:MaxPermSize=64M"

JMX="-Djava.rmi.server.hostname=127.0.0.1 -Dcom.sun.management.jmxremote.port=65100 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false"

PROJECT_ID="-DAPPNAME="$PROJECT_NAME

# nohup java ${JAVA_OPTS} -jar ${PROJECT_NAME}  1>/dev/null 2>nohup.err &

java ${JAVA_OPTS} ${PROJECT_ID} ${JMX} -jar ${PROJECT_NAME} 

echo $? >$PROJECT_DIR/$PID_FILE