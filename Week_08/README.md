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
	
	RecoveryService版本不一致问题，解决思路：
	看了shardingsphere的源码，里面transactions-jta、transactions-jdbc是4.0.6的版本，
	目前还不清楚为什么我的项目里面：shardingsphere-transaction-xa-core 5.0.0-alpha里面的transactions-jta、transactions-jdbc是3.9.6的版本，
	于是我把它们exclusion掉，然后引入4.0.6的版本，然后问题解决
	
	<dependency>
            <groupId>org.apache.shardingsphere</groupId>
            <artifactId>shardingsphere-transaction-xa-core</artifactId>
            <version>${shardingsphere.version}</version>
            <exclusions>
                <exclusion>
                    <groupId>com.atomikos</groupId>
                    <artifactId>transactions-jdbc</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>com.atomikos</groupId>
                    <artifactId>transactions-jta</artifactId>
                </exclusion>
            </exclusions>
        </dependency>

        <dependency>
            <groupId>com.atomikos</groupId>
            <artifactId>transactions-jdbc</artifactId>
            <version>4.0.6</version>
        </dependency>

        <dependency>
            <groupId>com.atomikos</groupId>
            <artifactId>transactions-jta</artifactId>
            <version>4.0.6</version>
        </dependency>