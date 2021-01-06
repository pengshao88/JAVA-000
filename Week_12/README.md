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
   9）在Redis6379 执行: set kk 01, 在Redis6380中执行：get kk，可以看到同步的数据。＜/br＞
   
   
Redis sentinel 配置：  
目前百度了这个文章：https://www.cnblogs.com/SecondSun/p/11270547.html   
明天继续把 sentinel高可用，Cluster集群 配置完，并争取把选做题demo也执行了。  
   
