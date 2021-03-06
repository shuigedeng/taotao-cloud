###########################################
cd /opt/taotao-cloud

wget http://download.redis.io/releases/redis-6.2.4.tar.gz

yum install tar -y
tar -zxvf redis-6.2.4.tar.gz

cd redis-6.2.4

yum install gcc cmake -y
make PREFIX=/opt/taotao-cloud/redis-6.2.4 install

mkdir -p {data, pid, logs}

vim redis.conf
# 主要修改这几项
bind 192.168.1.10
daemonize yes
pidfile /opt/taotao-cloud/redis-6.2.4/pid/redis_6379.pid
logfile "/opt/taotao-cloud/redis-6.2.4/logs/redis.log"
dir /opt/taotao-cloud/redis-6.2.4/data
requirepass taotao-cloud

#测试
redis-cli -h  192.168.1.10 -p 6379
登录后 auth taotao-cloud

#redis 自带的压测命令
redis-benchmark -h 192.168.1.10 -p 6379 -c 50 -n 10000

# 服务器可能在程序正在对 AOF 文件进行写入时停机， 如果停机造成了 AOF 文件出错（corrupt），
# 那么 Redis 在重启时会拒绝载入这个 AOF 文件， 从而确保数据的一致性不会被破坏。
# 这时候可以使用可以先使用 Redis 附带的 redis-check-aof 程序，
# 对原来的 AOF 文件进行修复，进而再启动redis
redis-check-aof --fix AOF文件

redis-check-rdb

##################### redis.sh #############################
#!/bin/bash

function start_redis() {
	/opt/taotao-cloud/redis-6.2.4/bin/redis-server /opt/taotao-cloud/redis-6.2.4/redis.conf
  sleep 10
  echo "redis started"
}

function stop_redis() {
	/opt/taotao-cloud/redis-6.2.4/bin/redis-cli -h 192.168.1.10 -a taotao-cloud shutdown 2>/dev/null
  sleep 10
  echo "redis stoped"
}

case $1 in
"start")
    start_redis
    ;;
"stop")
    stop_redis
    ;;
"restart")
    stop_redis
    sleep 10
    start_redis
    ;;
*)
    echo Invalid Args!
    echo 'Usage: '$(basename $0)' start|stop|restart'
    ;;
esac
