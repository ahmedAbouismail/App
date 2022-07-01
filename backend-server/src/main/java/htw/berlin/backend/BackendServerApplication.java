package htw.berlin.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@ComponentScan("htw.berlin")
public class BackendServerApplication {

//	@Bean
//	@LoadBalanced
//	public WebClient.Builder loadBalancedWebClientBuilder(){
//		return WebClient.builder();
//	}
	public static void main(String[] args) {
		SpringApplication.run(BackendServerApplication.class, args);
	}


}
