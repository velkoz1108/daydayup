package com.eden.flywaydemo;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.ClassicConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@RestController
@RequestMapping("/flyway")
public class FlywayController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/migrate")
    public String migrate() {
        ClassicConfiguration config = new ClassicConfiguration();
        config.setDataSource(dataSource);
        config.setBaselineOnMigrate(false);
        config.setBaselineVersionAsString("1");
        config.setLocationsAsStrings("db/migration", "com.eden.flywaydemo.migration");
        config.setTable("flyway_schema_history");

        Flyway flyway = new Flyway(config);

        int migrate = flyway.migrate();

        return "result: " + migrate;
    }

    @GetMapping("/repair")
    public String repair() {
        ClassicConfiguration config = new ClassicConfiguration();
        config.setDataSource(dataSource);
        config.setBaselineOnMigrate(false);
        config.setBaselineVersionAsString("1");
        //指定位置sql java
        config.setLocationsAsStrings("db/migration", "com.eden.flywaydemo.migration");
        config.setTable("flyway_schema_history");

        Flyway flyway = new Flyway(config);
        flyway.repair();
        return "success";
    }
}
