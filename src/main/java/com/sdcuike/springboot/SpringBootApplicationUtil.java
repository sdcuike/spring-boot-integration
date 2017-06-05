package com.sdcuike.springboot;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.ConfigurableEnvironment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by beaver on 2017/6/5.
 */
public final class SpringBootApplicationUtil {
    
    public static void runSpringBootApplication(Logger log, String[] args, Object... sources) throws UnknownHostException {
        SpringApplication springApplication = new SpringApplication(sources);
        ConfigurableEnvironment env = springApplication.run(args).getEnvironment();
        
        printInfo(env, log);
        
    }
    
    private static void printInfo(ConfigurableEnvironment env, Logger log) throws UnknownHostException {
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        log.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local: \t\t{}://localhost:{}\n\t" +
                        "External: \t{}://{}:{}\n\t" +
                        "server contextPath:\t{} \n\t" +
                        "Profile(s): \t{}\n----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                protocol,
                env.getProperty("server.port"),
                protocol,
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                env.getProperty("server.contextPath"),
                env.getActiveProfiles());
    }
    
    
}
