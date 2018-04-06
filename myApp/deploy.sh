#!/bin/bash
git pull
mvn clean install
ps -ef | grep myapp-0.0.1-SNAPSHOT.jar | grep -v grep | awk '{print $2}' | while read pid
do
    echo "myapp is running, to kill pid=$pid"
    kill -9 $pid
    echo "kill result: $?"
done
cd target
nohup java -jar myapp* >> out.log 2>&1 &
