package com.pyshankov.microservices;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import feign.codec.Encoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.feign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;


@FeignClient(serviceId = "user-service")
public interface UserService {

    @HystrixCommand(fallbackMethod = "defaultGet")
    @RequestMapping(method = RequestMethod.POST, value = "/login", consumes = "application/json", produces = "application/json")
    ResponseEntity<String> login(String user);

    default void defaultGet(Throwable e) {
        System.out.println("Inside defaultGet");
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,
                "user Exception");
    }

    class FeignSimpleEncoderConfig {
        @Bean
        public Encoder encoder() {
            ObjectFactory<HttpMessageConverters> objectFactory = () ->
                    new HttpMessageConverters(new FormHttpMessageConverter());
            return new SpringEncoder(objectFactory);
        }
    }
}
