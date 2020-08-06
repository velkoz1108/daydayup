package com.eden.flywaydemo.migration;

import org.flywaydb.core.api.MigrationVersion;
import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class V1_2__FixUsername extends BaseJavaMigration {
    //    private static final String VERSION = "V1_2__FixUsername";
    private static final String DESCRIPTION = "fix username";


    @Override
    public void migrate(Context context) throws Exception {
        DataSource dataSource = context.getConfiguration().getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        jdbcTemplate.execute("update user set username = 'eden1234' where id = 1 ");

    }

    @Override
    public MigrationVersion getVersion() {
//        return MigrationVersion.fromVersion(VERSION);
        return super.getVersion();
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public Integer getChecksum() {
        return 1;
    }

    @Override
    public boolean isUndo() {
        return super.isUndo();
    }

    @Override
    public boolean canExecuteInTransaction() {
        return super.canExecuteInTransaction();
    }
}
