# 整合案例一（使用Java API）
这种方式，官方已经明确表示在ES 7.0版本中将弃用TransportClient客户端，且在8.0版本中完全移除它。
es服务端版本 6.8.6
注意客户端版本不能高于这个版本，否则不兼容。

# 整合案例二(使用REST Clinet)
上面的案例1是基于TCP和ES通信的(而且TransPort将来会被抛弃……)，官方也给出了基于HTTP的客户端REST Client(推荐使用)，官方给出来的REST Client有Java Low Level REST Client和Java Hight Level REST Client两个，前者兼容所有版本的ES，后者是基于前者开发出来的，只暴露了部分API，待完善。这个案例中使用Java Low Level REST Client，有如下的一些特点：

最小化依赖；
提供跨所有可用节点的负载平衡；
提供节点故障和特定响应代码时的故障转移；
提供失败重连的惩罚机制(是否对一个连接失败的节点尝试重连，取决于它连续失败的次数，尝试重连且失败的次数越多，客户端在再次尝试重连这个节点时等的时间就越长。说那么多，太复杂了，其实给一个场景就是：我找你玩儿，你不答应，我伤自尊了，下次去找你我隔了一个星期再去找你，你又不答应，我又伤自尊了，下次再找你的话，那我就隔两个星期，依次类推)；
持久连接；
跟踪请求和响应的日志记录；
可选的集群节点自动发现功能；
这里使用的ES版本为最新版的6.4.0，其实官方推这个REST Client，个人还是觉得是因为它是基于HTTP端口去通信的，便于操作，而且跟ES版本几乎没有关系。

# 整合案例三（使用Spring-data-es）
除了上述方式，Spring也提供了本身基于SpringData实现的一套方案spring-data-elasticsearch，版本之间的搭配建议为：

spring data elasticsearch	elasticsearch
3.1.x	6.2.2
3.0.x	5.5.0
2.1.x	2.4.0
2.0.x	2.2.0
1.3.x	1.5.2
由于ES 6.4还没有被支持，这里选择的版本搭配为ES 6.2.2， Spring-data-es版本为3.1.0.BUILD-SNAPSHOT，结果整合失败，一直报错failed to load elasticsearch nodes : org.elasticsearch.client.transport.NoNodeAvailableException: None of the configured nodes are available，然后尝试换成Data ES 3.0.10版本成功整合，步骤如下：

