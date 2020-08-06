Prefix 前缀：V 为版本迁移，U 为回滚迁移，R 为可重复迁移。

Flyway 会给每个迁移脚本，计算出一个 checksum 字段。这样，每次启动时，都会校验已经安装( installed )的迁移脚本，是否发生了改变。如果是，抛出异常。这样，保证不会因为脚本变更，导致出现问题。

如果java类命名不符合约定就会提示错误
Skipping com.eden.flywaydemo.migration.FixUsername: (InvocationTargetException: null) caused by (FlywayException: Invalid Java-based migration class name: com.eden.flywaydemo.migration.FixUsername => ensure it starts with V or R, or implement org.flywaydb.core.api.migration.JavaMigration directly for non-default naming) at org.flywaydb.core.api.migration.BaseJavaMigration.<init>:67


Flyway Community Edition 6.4.4 by Redgate

U(Undo Migrations):
与version作用相反，版本号与V一致，Community版本不支持U


Use of dots (.) as path separators will be deprecated in Flyway 7. Path: com.eden.flywaydemo.migration

spring.flyway.enable=true
服务启动时自动执行