sharding-demo 是周四的作业

sharding-xa
2pc-xa-example 是周六的作业，直接使用了shardingsphere-example里面的两个例子。
shardingsphere的xa事务默认为atomikos，项目中可以直接使用

但是遇到了如下问题：
Exception in thread "main" java.lang.NoSuchMethodError: com.atomikos.icatch.RecoveryService.recover()V
	at com.atomikos.datasource.xa.XATransactionalResource.setRecoveryService(XATransactionalResource.java:435)
	at com.atomikos.icatch.config.Configuration.notifyAfterInit(Configuration.java:466)
	at com.atomikos.icatch.config.Configuration.init(Configuration.java:450)
	at com.atomikos.icatch.config.UserTransactionServiceImp.initialize(UserTransactionServiceImp.java:105)
	at com.atomikos.icatch.config.UserTransactionServiceImp.init(UserTransactionServiceImp.java:219)
	at org.apache.shardingsphere.transaction.xa.atomikos.manager.AtomikosTransactionManager.init(AtomikosTransactionManager.java:43)
	at org.apache.shardingsphere.transaction.xa.XAShardingTransactionManager.init(XAShardingTransactionManager.java:55)
	at org.apache.shardingsphere.transaction.ShardingTransactionManagerEngine.init(ShardingTransactionManagerEngine.java:67)
	at org.apache.shardingsphere.driver.jdbc.core.datasource.ShardingSphereDataSource.createTransactionContexts(ShardingSphereDataSource.java:83)
	at org.apache.shardingsphere.driver.jdbc.core.datasource.ShardingSphereDataSource.<init>(ShardingSphereDataSource.java:59)
	at org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory.createDataSource(ShardingSphereDataSourceFactory.java:49)
	at org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory.createDataSource(YamlShardingSphereDataSourceFactory.java:53)
	at com.yezp.xa_2pc.example.XAOrderService.<init>(XAOrderService.java:38)
	at com.yezp.xa_2pc.example.Application.main(Application.java:9)
	
	RecoveryService版本不一致问题，明天把shardingsphere的源码下载下来定位一下问题。