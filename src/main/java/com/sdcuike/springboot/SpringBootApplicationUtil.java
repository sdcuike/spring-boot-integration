package com.sdcuike.springboot;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.ConfigurableEnvironment;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

/**
 * Created by beaver on 2017/6/5.
 */
public final class SpringBootApplicationUtil {
    
    public static void runSpringBootApplication(Logger log, String[] args, Object... sources) throws UnknownHostException {
        SpringApplication springApplication = new SpringApplication(sources);
        ConfigurableEnvironment env = springApplication.run(args).getEnvironment();
        
        printInfo(env, args, log);
        
    }
    
    private static void printInfo(ConfigurableEnvironment env, String[] args, Logger log) throws UnknownHostException {
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
        
        String mainArguments = Stream.of(args).map(t -> t + "\n\t").collect(joining());
        
        log.info("\n\n\n------------------main method Arguments----------------------------\n\n\n {}", mainArguments);
        
        
        Properties properties = System.getProperties();
        
        StringWriter sb = new StringWriter();
        PrintWriter pw = new PrintWriter(sb);
        properties.list(pw);
        pw.flush();
        log.info("\n\n\n------------------System  Properties----------------------------\n\n\n {}", sb.toString());
        
        
        String systemEnv = System.getenv().entrySet().stream().map(t -> t.getKey() + ":\t\t\t\t\t" + t.getValue() + "\n\t").collect(joining());
        log.info("\n\n\n------------------System  environment----------------------------\n\n\n {}", systemEnv);
        
        
        RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
        List<String> arguments = runtimeMxBean.getInputArguments();
        String jvmArguments = arguments.stream().map(t -> t + " \n").collect(joining());
        
        log.info("\n\n\n------------------JVM  Arguments----------------------------\n\n\n {}", jvmArguments);
        
    }
    
    
}
