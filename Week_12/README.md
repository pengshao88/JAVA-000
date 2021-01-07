学习笔记

12课作业

Redis主从配置：  
1、使用解压直接使用版本：”Redis-x64-3.2.100“，redis.windows_6379.conf是主库的配置文件  
2、拷贝一份Redis-x64-3.2.100，并修改为：Redis-x64-3.2.100_6380  
3、把Redis-x64-3.2.100_6380里面的redis.windows.conf 改为redis.windows_6380.conf  
   redis.windows_6380.conf 里面的 port 修改为6380  
4、1）在cmd中：执行redis-server --service-install redis.windows.conf  --service-name Redis6379  将其注册为windows的服务；  
   2）再执行redis-server.exe redis.windows_6379.conf 把Redis6379服务跑起来；  
   3）执行redis-cli.exe 连接上redis6379数据库；  
   3）另起一个cmd中：执行redis-server --service-install redis.windows.conf  --service-name Redis6380  将其注册为windows的服务；  
   4）再执行redis-server.exe redis.windows_6380.conf 把Redis6380服务跑起来；  
   5）执行redis-cli.exe -h 127.0.0.1 -p 6380 连接上redis6380数据库；  
   6）在6380中，执行slaveof 127.0.0.1 6379 设置为Redis6379的从库；  
   7）分别执行 info 命令，查看对应的信息；可以看到Redis6379是：role:master, Redis6380是：role:slave；  
   8）在Redis6380中执行：set kk 01 提示：“(error) READONLY You can't write against a read only slave.”；  
   9）在Redis6379 执行: set kk 01, 在Redis6380中执行：get kk，可以看到同步的数据。  
   
   
Redis sentinel 配置：  
参考文章：https://www.cnblogs.com/SecondSun/p/11270547.html   
1、在主从的基础上再拷贝一份Redis-x64-3.2.100_6381；  
2、配置sentinel.conf文件，配置内容：  
   port 26379                                      #哨兵节点端口  
   sentinel monitor mymaster 127.0.0.1 6379 1      #监听6379主库  
   sentinel down-after-milliseconds mymaster 5000  #5秒后选举新的主库  
   sentinel failover-timeout mymaster 15000        #配置所有slaves指向新的master所需的最大时间
3、拷贝两份sentinel.conf文件，将端口分别改为26380 26381 分别放在6380、6381目录下； 
4、在6379目录下执行：redis-server --service-install sentinel.conf --loglevel verbose  --service-name sentinel26379 --sentinel  
   然后再执行redis-server --service-start --service-name sentinel26379  
5、分别在6380 6381 执行上述两条命令（修改redis服务名称）
6、cmd下执行：redis-cli.exe -p 26379，登录到sentinel26379
   info sentinel 查看sentinel信息，可以看到两个从库，3个哨兵  
7、将6379服务shutdown，5秒后，info sentinel 可以看到mymaster 自动变成了6380  


Redis 集群配置：  
参考文章：https://www.cnblogs.com/thirteen-zxh/p/9187875.html  
1、分别把三个redis.windows.conf 文件中的配置修改：  
   cluster-enabled yes  
   cluster-config-file nodes-6379.conf  
   cluster-node-timeout 15000
2、下载ruby，ruby今天没有下载完成，阻塞了 o(╥﹏╥)o  


明天继续......
   
