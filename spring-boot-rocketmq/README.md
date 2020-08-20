#RocketMQ
## 参考
http://jm.taobao.org/2017/01/12/rocketmq-quick-start-in-10-minutes/
https://www.cnblogs.com/qdhxhz/p/11191399.html
https://blog.csdn.net/dancheren/article/details/71373970

## 基本概念

## MQ发送普通消息(三种方式)
### 可靠同步发送消息[send(Message msg,SendCallback sendCallback)]

### 可靠异步发送消息[send(Message msg)]

### 单向发送消息[sendOneway(Message msg)]


## 服务启动
### 启动Server
```shell script
nohup sh bin/mqnamesrv &
```
### 启动Broker
```shell script
nohup sh bin/mqbroker -n localhost:9876 &
```

## WEB查询插件
```yaml
https://gitee.com/xutingyin/rocketmq-externals.git
```
