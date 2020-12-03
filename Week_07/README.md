学习笔记:
暂时只完成上周四的插入100万条数据作业；
使用事务分批次提交耗时：
// ======> use 94520ms
// ======> 加上rewriteBatchedStatements=true后 use 59354ms

有空再继续优化

动态切换数据源1.0版本参考：https://github.com/specialhy/demo/tree/master/readorwrite

第二个作业能跑通，但感觉配置主从好像不对，与低版本的配置方式不一样。
