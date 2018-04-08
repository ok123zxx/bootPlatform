#!/bin/bash
cd ..
git pull
mvn clean install


ps -ef | grep boot-schedule-1.0-SNAPSHOT.jar | grep -v grep | awk '{print $2}' | while read pid
do
    echo "boot-schedule is running, to kill pid=$pid"
    kill -9 $pid
    echo "kill result: $?"
done

ps -ef | grep myapp-0.0.1-SNAPSHOT.jar | grep -v grep | awk '{print $2}' | while read pid
do
    echo "myapp is running, to kill pid=$pid"
    kill -9 $pid
    echo "kill result: $?"
done



nohup java -jar bootSchedule/target/boot-schedule-1.0-SNAPSHOT.jar >> boot.log 2>&1 &

nohup java -jar myApp/target/myapp-0.0.1-SNAPSHOT.jar >> app.log 2>&1 &