# SpringBoot 整合ElasticSearch
## 搭建ES的集群
### master 的elasticsearch.yml
```yaml
    cluster.name: xutingyin-cluster
    node.name: master
    node.master: true
    network.host: 127.0.0.1
    http.port: 9200
    transport.tcp.port: 9300
    discovery.zen.ping.unicast.hosts: ["127.0.0.1:9300","127.0.0.1:9310","127.0.0.1:9320"]
    cluster.initial_master_nodes: ["master"]
    # 配置head插件所需
    # 是否支持跨域，默认为false
    http.cors.enabled: true
    # 当设置允许跨域，默认为*,表示支持所有域名
    http.cors.allow-origin: "*"
```
### slave1 的elasticsearch.yml
```yaml
   cluster.name: xutingyin-cluster
   node.name: slave1
   network.host: 127.0.0.1
   http.port: 9210
   transport.tcp.port: 9310
   discovery.zen.ping.unicast.hosts: ["127.0.0.1:9300","127.0.0.1:9310","127.0.0.1:9320"]
   cluster.initial_master_nodes: ["master"]
   # 配置head插件所需
   # 是否支持跨域，默认为false
   http.cors.enabled: true
   # 当设置允许跨域，默认为*,表示支持所有域名
   http.cors.allow-origin: "*"
```
### slave2 的elasticsearch.yml
```yaml
    cluster.name: xutingyin-cluster
    node.name: slave2
    network.host: 127.0.0.1
    http.port: 9220
    transport.tcp.port: 9320
    discovery.zen.ping.unicast.hosts: ["127.0.0.1:9300","127.0.0.1:9310","127.0.0.1:9320"]
    cluster.initial_master_nodes: ["master"]
    # 配置head插件所需
    # 是否支持跨域，默认为false
    http.cors.enabled: true
    # 当设置允许跨域，默认为*,表示支持所有域名
    http.cors.allow-origin: "*"
```