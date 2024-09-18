package com.example.ApiGateway.Config;

import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import java.util.List;


@Component
public class RouteValidators {

    private static final List<String> OPEN_POST_ROUTES = List.of("/hirer", "/freelancer", "/company", "/auth");

    public boolean isRoleRequiredForEndpoint(ServerWebExchange exchange, List<String> roles) {
        ServerHttpRequest request = exchange.getRequest();
        String requestPath = fixRequestPath(request.getURI().getPath());
        HttpMethod requestMethod = request.getMethod();
        return Routes.ROUTES_LIST.stream()
                .filter(route -> requestPath.matches(route.getPath()))
                .map(Routes::getEndpointRoles)
                .filter(endpointRoles -> endpointRoles.containsKey(requestMethod))
                .map(endpointRoles -> endpointRoles.get(requestMethod))
                .anyMatch(endpointRoles -> endpointRoles.stream().anyMatch(roles::contains));
    }


    public boolean isSecured(ServerHttpRequest request) {
        String requestPath = fixRequestPath(request.getURI().getPath());
        HttpMethod requestMethod = request.getMethod();
        if (requestMethod == HttpMethod.POST && OPEN_POST_ROUTES.stream().anyMatch(requestPath::startsWith)) {
            return false;
        }else{
            return Routes.ROUTES_LIST.stream()
                .anyMatch(route -> requestPath.matches(route.getPath()));
        }
    }

    private String fixRequestPath(String requestPath) {
        if ("/bff/task".equals(requestPath) || "/bff/request".equals(requestPath)) {
            return requestPath.replaceAll("^(?:[^/]*/){3}([^/]*/).*", "/$1");
        } else {
            return requestPath.replaceAll("^(/[^/]+).*", "$1");
        }
    }
}
