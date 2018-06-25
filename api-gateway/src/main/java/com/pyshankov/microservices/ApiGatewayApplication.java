package com.pyshankov.microservices;

import com.pyshankov.microservices.hazelcast.cache.HazelcastClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Import(HazelcastClientConfig.class)
@RestController
@EnableZuulProxy
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ApiGatewayApplication {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private UserService userService;


    @Bean
    RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
        return restTemplate;
    }


    @RequestMapping(value = "server/{id}")
    public List<ServiceInstance> availableServers(@PathVariable String id) {
        return discoveryClient.getInstances(id);
    }

    @RequestMapping(value = "/login", method = POST)
    public ResponseEntity<String> loginTo(@RequestBody String user) {
        ResponseEntity<String> responseEntity = userService.login(user);
        return responseEntity;
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public ZuulFallbackProvider zuulFallbackProviderStatistic() {
        return new ZuulFallbackProvider() {

            @Override
            public String getRoute() {
                // Might be confusing: it's the serviceId property and not the route
                return "statistic-service";
            }

            @Override
            public ClientHttpResponse fallbackResponse() {
                return new ClientHttpResponse() {
                    @Override
                    public HttpStatus getStatusCode() throws IOException {
                        return HttpStatus.OK;
                    }

                    @Override
                    public int getRawStatusCode() throws IOException {
                        return HttpStatus.OK.value();
                    }

                    @Override
                    public String getStatusText() throws IOException {
                        return HttpStatus.OK.toString();
                    }

                    @Override
                    public void close() {
                    }

                    @Override
                    public InputStream getBody() throws IOException {
                        return new ByteArrayInputStream("{\"factorA\":\"Sorry, Service is Down!\",\"factorB\":\"?\",\"id\":null}".getBytes());
                    }

                    @Override
                    public HttpHeaders getHeaders() {
                        HttpHeaders headers = new HttpHeaders();
                        headers.setContentType(MediaType.APPLICATION_JSON);
                        return headers;
                    }
                };
            }
        };
    }

    @Bean
    public ZuulFallbackProvider zuulFallbackProviderProduct() {
        return new ZuulFallbackProvider() {

            @Override
            public String getRoute() {
                // Might be confusing: it's the serviceId property and not the route
                return "product-service";
            }

            @Override
            public ClientHttpResponse fallbackResponse() {
                return new ClientHttpResponse() {
                    @Override
                    public HttpStatus getStatusCode() throws IOException {
                        return HttpStatus.OK;
                    }

                    @Override
                    public int getRawStatusCode() throws IOException {
                        return HttpStatus.OK.value();
                    }

                    @Override
                    public String getStatusText() throws IOException {
                        return HttpStatus.OK.toString();
                    }

                    @Override
                    public void close() {
                    }

                    @Override
                    public InputStream getBody() throws IOException {
                        return new ByteArrayInputStream("{\"factorA\":\"Sorry, Service is Down!\",\"factorB\":\"?\",\"id\":null}".getBytes());
                    }

                    @Override
                    public HttpHeaders getHeaders() {
                        HttpHeaders headers = new HttpHeaders();
                        headers.setContentType(MediaType.APPLICATION_JSON);
                        return headers;
                    }
                };
            }
        };
    }

    @Bean
    public ZuulFallbackProvider zuulFallbackProviderUser() {
        return new ZuulFallbackProvider() {

            @Override
            public String getRoute() {
                // Might be confusing: it's the serviceId property and not the route
                return "user-service";
            }

            @Override
            public ClientHttpResponse fallbackResponse() {
                return new ClientHttpResponse() {
                    @Override
                    public HttpStatus getStatusCode() throws IOException {
                        return HttpStatus.OK;
                    }

                    @Override
                    public int getRawStatusCode() throws IOException {
                        return HttpStatus.OK.value();
                    }

                    @Override
                    public String getStatusText() throws IOException {
                        return HttpStatus.OK.toString();
                    }

                    @Override
                    public void close() {
                    }

                    @Override
                    public InputStream getBody() throws IOException {
                        return new ByteArrayInputStream("{\"factorA\":\"Sorry, Service is Down!\",\"factorB\":\"?\",\"id\":null}".getBytes());
                    }

                    @Override
                    public HttpHeaders getHeaders() {
                        HttpHeaders headers = new HttpHeaders();
                        headers.setContentType(MediaType.APPLICATION_JSON);
                        return headers;
                    }
                };
            }
        };
    }
}

