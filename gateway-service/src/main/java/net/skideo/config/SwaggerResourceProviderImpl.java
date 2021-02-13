package net.skideo.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
@EnableAutoConfiguration
public class SwaggerResourceProviderImpl implements SwaggerResourcesProvider {
    @Override
    public List<SwaggerResource> get() {
        List resources = new ArrayList<>();
        resources.add(swaggerResource("academy-service", "/academy/v2/api-docs", "2.0"));
        resources.add(swaggerResource("club-service", "/club/v2/api-docs", "2.0"));
        resources.add(swaggerResource("player-service", "/player/v2/api-docs", "2.0"));
        resources.add(swaggerResource("scout-service", "/scout/v2/api-docs", "2.0"));
        resources.add(swaggerResource("notification-service", "/notification/v2/api-docs", "2.0"));
        resources.add(swaggerResource("oauth-service", "/oauth/v2/api-docs", "2.0"));
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }

}
