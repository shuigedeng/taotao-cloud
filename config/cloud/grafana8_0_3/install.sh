###########################################
# https://grafana.com/grafana/download

cd /root/taotao-cloud/grafana8.0.3

wget https://dl.grafana.com/oss/release/grafana-8.0.3.linux-amd64.tar.gz

tar -zxvf grafana-8.0.3.linux-amd64.tar.gz

mv grafana-8.0.3.linux-amd64 grafana8.0.3

##################### grafana.sh #############################
#!/bin/bash

function start_grafana() {
  nohup /opt/taotao-cloud/grafana8.0.3/bin/grafana-server \
  -homepath /opt/taotao-cloud/grafana8.0.3 \
   >/opt/taotao-cloud/grafana8.0.3/start.out 2>&1 &

  sleep 10
  echo "grafana started"
}

function stop_grafana() {
    ps -ef | grep grafana|grep -v grep|awk '{print $2}' |xargs kill -9

    sleep 10
    echo "grafana stoped"
}

case $1 in
"start")
    start_grafana
    ;;
"stop")
    stop_grafana
    ;;
"restart")
    stop_grafana
    sleep 10
    start_grafana
    ;;
*)
    echo Invalid Args!
    echo 'Usage: '$(basename $0)' start|stop|restart'
    ;;
esac
