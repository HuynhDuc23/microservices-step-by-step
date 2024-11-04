package jmaster.gateway_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
		return builder.routes()
                .route("account-service", r -> r.path("/user/**")
						.filters(f->f.stripPrefix(1).circuitBreaker(c->c.setName("CircuiltBreaker")))
                        .uri("lb://account-service"))
                .route("rest-api", r -> r.path("/api/**")
						 .filters(f->f.stripPrefix(1).circuitBreaker(c->c.setName("CircuiltBreaker")))
                        .uri("lb://rest-api"))
                .route("notification-service", r -> r.path("/notification/**")
						.filters(f->f.stripPrefix(1).circuitBreaker(c->c.setName("CircuiltBreaker")))
                        .uri("lb://notification-service"))
                .route("statistic-service", r -> r.path("/statistic/**")
                        .uri("lb://statistic-service"))
                .build();
		/// swagger ui

	}

}
