package com.example.ApiGateway.Config;

import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import java.util.List;


@Component
public class RouteValidators {
    public boolean isRoleRequiredForEndpoint(ServerWebExchange exchange, List<String> roles) {
        ServerHttpRequest request = exchange.getRequest();
        String requestPath = request.getURI().getPath();
        HttpMethod requestMethod = request.getMethod();

        return Routes.ROUTES_LIST.stream()
                .filter(route -> requestPath.matches(route.getPath()))
                .map(Routes::getEndpointRoles)
                .filter(endpointRoles -> endpointRoles.containsKey(requestMethod))
                .map(endpointRoles -> endpointRoles.get(requestMethod))
                .anyMatch(endpointRoles -> endpointRoles.stream().anyMatch(roles::contains));
    }


    public boolean isSecured(ServerHttpRequest request) {
        String requestPath = request.getURI().getPath();
        return Routes.ROUTES_LIST.stream()
                .map(Routes::getPath)
                .anyMatch(requestPath::matches);
    }
}
