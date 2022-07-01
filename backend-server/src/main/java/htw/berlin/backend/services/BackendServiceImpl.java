package htw.berlin.backend.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import htw.berlin.api.backend.ChartAggregate;
import htw.berlin.api.backend.IBackendService;
import htw.berlin.util.http.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;


import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;

import static java.util.logging.Level.FINE;
import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RestController
public class BackendServiceImpl{

    private static final Logger LOG = LoggerFactory.getLogger(BackendServiceImpl.class);

    private static final String CHART_SERVICE_URL = "https://localhost:8443/chart-composite";

    private final WebClient webClient;
    private final ObjectMapper mapper;

    private final ServiceUtil serviceUtil;

    @Autowired
    public BackendServiceImpl(WebClient.Builder webClientBuilder, ObjectMapper mapper, ServiceUtil serviceUtil) {
        this.webClient = webClientBuilder.build();
        this.mapper = mapper;
        this.serviceUtil = serviceUtil;
    }


    @GetMapping(value = "/test")
    public String getTest(){

        String txt = "Test";
        LOG.info(txt);
        return txt;
    }


    @GetMapping(value = "/try/{chartId}")
    public ChartAggregate getChart2(@PathVariable int chartId,
                                    @RegisteredOAuth2AuthorizedClient("writer-client-authorization-code") OAuth2AuthorizedClient authorizedClient) {
        URI url = UriComponentsBuilder.fromUriString(CHART_SERVICE_URL + "/{chartId}").build(chartId);
        LOG.debug("Will call the getChart API on URL: {}", url);

        return webClient.get().uri(url)
                .attributes(oauth2AuthorizedClient(authorizedClient))
                .retrieve().bodyToMono(ChartAggregate.class).log(LOG.getName(), FINE)
                .onErrorResume(error -> Mono.empty()).block();
    }

}
//
//    // '/authorized' is the registered 'redirect_uri' for authorization_code
//    @GetMapping(value = "/authorized", params = OAuth2ParameterNames.ERROR)
//    public String authorizationFailed(Model model, HttpServletRequest request) {
//        String errorCode = request.getParameter(OAuth2ParameterNames.ERROR);
//        if (StringUtils.hasText(errorCode)) {
//            model.addAttribute("error",
//                    new OAuth2Error(
//                            errorCode,
//                            request.getParameter(OAuth2ParameterNames.ERROR_DESCRIPTION),
//                            request.getParameter(OAuth2ParameterNames.ERROR_URI))
//            );
//        }
//
//        return "index";
//    }
//
