#!/bin/bash

function start_skywalking() {
     nohup sh /opt/taotao-cloud/skywalking8.6.0/bin/startup.sh  >/opt/taotao-cloud/skywalking8.6.0/start.log 2>&1 &
     sleep 10
     echo " skywalking started"
}

function stop_skywalking() {
     ps -ef | grep skywalking|grep -v grep|awk '{print $2}' |xargs kill -9
     sleep 10
     echo "skywalking stoped"
}

case $1 in
"start")
    start_skywalking
    ;;
"stop")
    stop_skywalking
    ;;
"restart")
    stop_skywalking
    sleep 15
    start_skywalking
    ;;
*)
    echo Invalid Args!
    echo 'Usage: '$(basename $0)' start|stop|restart'
    ;;
esac
