package com.example.ApiGateway.Config;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class RouteValidators {

    public static final Map<String, List<String>> endpointRoles = Map.of(
            "/users/*", List.of("HIRER","COMPANY","FREELANCER"),
            "/hirer/*", List.of("HIRER"),
            "/company/*", List.of("COMPANY"),
            "/freelancer/*", List.of("FREELANCER"),
            "/task/*", List.of("COMPANY","FREELANCER"),
            "/request/*", List.of("HIRER"),
            "/category/*", List.of("COMPANY","FREELANCER"),
            "/chat/*", List.of("HIRER","COMPANY","FREELANCER"),
            "/message/*", List.of("HIRER","COMPANY","FREELANCER"),
            "/feedback/*", List.of("HIRER","COMPANY","FREELANCER")
    );

    public boolean isRoleRequiredForEndpoint(ServerHttpRequest request, List<String> roles) {
        String requestPath = request.getURI().getPath();
        return endpointRoles.entrySet().stream()
                .filter(entry -> requestPath.matches(entry.getKey()))
                .anyMatch(entry -> entry.getValue().stream().anyMatch(roles::contains));
    }


    public boolean isSecured(ServerHttpRequest request) {
        String requestPath = request.getURI().getPath();
        return endpointRoles.keySet().stream()
                .anyMatch(requestPath::matches);
    }
}
