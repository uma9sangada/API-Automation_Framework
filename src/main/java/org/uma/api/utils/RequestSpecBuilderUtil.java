package org.uma.api.utils; 

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.uma.api.utils.ConfigurationManager;

public class RequestSpecBuilderUtil{ 

    public static RequestSpecification getRequestSpec() {
        ConfigurationManager config = ConfigurationManager.getInstance();

        return new RequestSpecBuilder()
                .setBaseUri(config.getBaseUrl())
                .setContentType("application/json")
                // Add any other default headers or configurations here
                // .addHeader("Authorization", "Bearer " + config.getAccessToken())
                // .addQueryParam("apiKey", config.getApiKey())
                .build();
    }
}