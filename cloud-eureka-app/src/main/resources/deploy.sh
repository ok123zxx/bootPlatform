#!/usr/bin/env bash
# multi instance eureka

ps -ef | grep cloud-eureka-app-1.0-SNAPSHOT.jar | grep -v grep | awk '{print $2}' | while read pid
do
    echo "cloud-eureka-app-1.0-SNAPSHOT.jar is running, to kill pid=$pid"
    kill -9 $pid
    echo "kill result: $?"
done

cd /data/projects/bootPlatform/cloud-eureka-app/target
nohup java -jar cloud-eureka-app-1.0-SNAPSHOT.jar --spring.profiles.active=peer1 >> output.log 2>&1 &
nohup java -jar cloud-eureka-app-1.0-SNAPSHOT.jar --spring.profiles.active=peer2 >> output.log 2>&1 &
nohup java -jar cloud-eureka-app-1.0-SNAPSHOT.jar --spring.profiles.active=peer3 >> output.log 2>&1 &
tail -f output.log