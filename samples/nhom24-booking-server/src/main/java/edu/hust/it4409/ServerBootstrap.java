package edu.hust.it4409;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

import edu.hust.it4409.web.WebConfig;

public class ServerBootstrap {
    
    public static void main(String[] args) {
        // @formatter:off
        var spring = new SpringApplicationBuilder(ServerConfiguration.class)
                .profiles("dev", "pg")
                .web(WebApplicationType.NONE)
                .registerShutdownHook(true)
            .child(WebConfig.class)
                .web(WebApplicationType.SERVLET)
            .run(args);
        // @formatter:on
    }
    
}
