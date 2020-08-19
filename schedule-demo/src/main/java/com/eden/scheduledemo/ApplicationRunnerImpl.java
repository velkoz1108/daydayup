package com.eden.scheduledemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunnerImpl implements ApplicationRunner {
    @Autowired
    private ConfigurableEnvironment environment;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("通过实现ApplicationRunner接口，在spring boot项目启动后打印参数");
        String[] sourceArgs = args.getSourceArgs();
        for (String arg : sourceArgs) {
            System.out.print(arg + " ");
        }
        System.out.println();


        MutablePropertySources propertySources = environment.getPropertySources();
        propertySources.forEach(
                System.out::print
        );

        StandardEnvironment standardEnvironment = new StandardEnvironment();
        standardEnvironment.getPropertySources().forEach(
                System.out::println
        );
    }
}
