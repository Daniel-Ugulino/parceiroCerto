package com.example.ApiGateway.Config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
public class Routes {
    private String path;
    private Map<HttpMethod, List<String>>endpointRoles = new HashMap<>();

    public static final List<Routes> ROUTES_LIST;
    static {
            ROUTES_LIST = List.of(
                    new Routes() {{
                        setPath("/users/*");
                        setEndpointRoles(Map.of(
                                HttpMethod.GET, List.of("HIRER", "COMPANY", "FREELANCER"),
                                HttpMethod.PUT, List.of("HIRER", "COMPANY", "FREELANCER"),
                                HttpMethod.DELETE, List.of("HIRER", "COMPANY", "FREELANCER")
                        ));
                    }},
                new Routes() {{
                    setPath("/hirer/*");
                    setEndpointRoles(Map.of(
                            HttpMethod.GET, List.of("HIRER", "COMPANY", "FREELANCER"),
                            HttpMethod.PUT, List.of("HIRER"),
                            HttpMethod.PATCH, List.of("HIRER")
                    ));
                }},
                new Routes() {{
                    setPath("/company/*");
                    setEndpointRoles(Map.of(
                            HttpMethod.GET, List.of("HIRER", "COMPANY", "FREELANCER"),
                            HttpMethod.PUT, List.of("COMPANY"),
                            HttpMethod.PATCH, List.of("COMPANY")
                    ));
                }},
                new Routes() {{
                    setPath("/freelancer/*");
                    setEndpointRoles(Map.of(
                            HttpMethod.GET, List.of("HIRER", "COMPANY", "FREELANCER"),
                            HttpMethod.PUT, List.of("FREELANCER"),
                            HttpMethod.PATCH, List.of("FREELANCER")
                    ));
                }},
                new Routes() {{
                    setPath("/task/*");
                    setEndpointRoles(Map.of(
                            HttpMethod.POST, List.of("COMPANY", "FREELANCER"),
                            HttpMethod.GET, List.of("HIRER", "COMPANY", "FREELANCER"),
                            HttpMethod.PUT, List.of("COMPANY", "FREELANCER"),
                            HttpMethod.PATCH, List.of("COMPANY", "FREELANCER")
                    ));
                }},
                new Routes() {{
                    setPath("/request/*");
                    setEndpointRoles(Map.of(
                            HttpMethod.POST, List.of("HIRER"),
                            HttpMethod.GET, List.of("HIRER", "COMPANY", "FREELANCER"),
                            HttpMethod.PUT, List.of("COMPANY", "FREELANCER"),
                            HttpMethod.PATCH, List.of("HIRER", "COMPANY", "FREELANCER")
                    ));
                }},
                new Routes() {{
                    setPath("/category/*");
                    setEndpointRoles(Map.of(
                            HttpMethod.GET, List.of("HIRER", "COMPANY", "FREELANCER"),
                            HttpMethod.POST, List.of("HIRER", "COMPANY", "FREELANCER")
                    ));
                }},
                new Routes() {{
                    setPath("/chat/*");
                    setEndpointRoles(Map.of(
                            HttpMethod.GET, List.of("HIRER", "COMPANY", "FREELANCER")
                    ));
                }},
                new Routes() {{
                    setPath("/message/*");
                    setEndpointRoles(Map.of(
                            HttpMethod.POST, List.of("HIRER", "COMPANY", "FREELANCER"),
                            HttpMethod.GET, List.of("HIRER", "COMPANY", "FREELANCER")
                    ));
                }},
                new Routes() {{
                    setPath("/feedback/*");
                    setEndpointRoles(Map.of(
                            HttpMethod.POST, List.of("HIRER"),
                            HttpMethod.GET, List.of("HIRER", "COMPANY", "FREELANCER")
                    ));
                }},
                new Routes() {{
                    setPath("/bff/task");
                    setEndpointRoles(Map.of(
                            HttpMethod.POST, List.of("COMPANY", "FREELANCER")
                    ));
                }},
                new Routes() {{
                    setPath("/bff/request");
                    setEndpointRoles(Map.of(
                            HttpMethod.POST, List.of("HIRER")
                    ));
                }},
                new Routes() {{
                    setPath("/bff/feedback");
                    setEndpointRoles(Map.of(
                            HttpMethod.POST, List.of("HIRER")
                    ));
                }}
       );
    }
}
