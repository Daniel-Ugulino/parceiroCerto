package com.example.ApiGateway.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    RouteValidators routeValidators;

    @Autowired
    TokenProvider tokenProvider;

    public AuthenticationFilter() {
        super(Config.class);
    }

    public static class Config{

    }

    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {
            if(routeValidators.isSecured((exchange.getRequest()))) {
                try {
                    List<HttpCookie> cookie = exchange.getRequest().getCookies().get("access_token");
                    if(cookie != null && !cookie.isEmpty()) {
                        String jwt = cookie.get(0).getValue();
                        List<String> roles = tokenProvider.extractRole(jwt);
                        if (!tokenProvider.isTokenValid(jwt) || !routeValidators.isRoleRequiredForEndpoint(exchange, roles)) {
                            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid Role");
                        }
                    }
                } catch (Exception exception) {
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access Token Required");
                }
            }
            return chain.filter(exchange);
        }));
    }
}
