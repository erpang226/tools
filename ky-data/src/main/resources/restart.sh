#!/bin/sh

# app name
app_name=ky-data-0.0.1-SNAPSHOT.jar
# app dir
app_file=/home/apps/jars/${app_name}

process=`ps -ef|grep java|grep ${app_name}|grep -v grep|grep -v PPID|awk '{ print $2}'`
for p in ${process}
do
  echo "Kill the $app_name process [ $p ]"
  kill -9 $p
done

echo "$app_name 启动中..."
nohup java -jar ${app_file} &

ps -ef|grep java|grep ${app_name}
tail -f nohup.out
