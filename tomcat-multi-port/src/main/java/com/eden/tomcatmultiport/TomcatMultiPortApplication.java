package com.eden.tomcatmultiport;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardService;
import org.apache.coyote.ProtocolHandler;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
public class TomcatMultiPortApplication {

    public static void main(String[] args) {
        SpringApplication.run(TomcatMultiPortApplication.class, args);
    }

    @GetMapping("/test")
    public String test() {
        double random = Math.random() / 10000;
        while (true) {
            if (random > 10000L) {
                break;
            }
            try {
                Thread.sleep(1000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "hello";
    }

    @GetMapping("/testPort")
    public String testPort() {
        return "hello";
    }

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> webServerFactoryCustomizer() {
        return factory -> {
            Connector connector = new Connector();
            connector.setPort(8098);

            AdvancedThreadExecutor executor = new AdvancedThreadExecutor();

            try {
                executor.startInternal();
            } catch (LifecycleException e) {
                e.printStackTrace();
            }

            connector.setService(new StandardService());

            connector.getService().addExecutor(executor);

            ProtocolHandler handler = connector.getProtocolHandler();
            Http11NioProtocol http11NioProtocol = (Http11NioProtocol) handler;
            http11NioProtocol.setExecutor(executor);


            factory.addAdditionalTomcatConnectors(connector);
        };
    }
}
