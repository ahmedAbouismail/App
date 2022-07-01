package htw.berlin.api.backend;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

public interface IBackendService {

    @PostMapping(
            value = "/app-api",
            consumes = "application/json"
    )
    Mono<Void> createChart(@RequestBody ChartAggregate body);

    @GetMapping(
            value = "/app-api/{chartId}",
            produces = "application/json"
    )
    Mono<ChartAggregate> getChart(@PathVariable int chartId, @RegisteredOAuth2AuthorizedClient("writer-client-authorization-code") OAuth2AuthorizedClient authorizedClient);

    @PatchMapping(
            value = "/app-api",
            consumes = "application/json"
    )
    Mono<ChartAggregate> updateChart(@RequestBody ChartAggregate body);

    @DeleteMapping(
            value = "/app-api/{chartId}",
            consumes = "application/json"
    )
    Mono<Void> deleteChart(@PathVariable int chartId);
}
