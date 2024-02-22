package it.contrader.impiantoservice.feignClient;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {

    @Autowired
    private FeignClientInterceptor feignClientInterceptor;


    @Bean
    public RequestInterceptor requestInterceptor(){
        return feignClientInterceptor;
    }
}
