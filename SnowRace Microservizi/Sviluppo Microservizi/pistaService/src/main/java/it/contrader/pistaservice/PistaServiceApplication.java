package it.contrader.pistaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class PistaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PistaServiceApplication.class, args);
    }

}
