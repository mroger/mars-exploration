package org.nasa.exploration.api.configuration;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("org.nasa.exploration.api.controller"))
            .paths(PathSelectors.regex("/probes.*"))
            .build()
            .useDefaultResponseMessages(false)
            .apiInfo(apiInfo());
    }

    //String title, String description, String version, String termsOfServiceUrl, Contact contact, String license, String licenseUrl, Collection<VendorExtension> vendorExtensions
    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
            "Mars exploration API",
            "This API was built to manage Probes sent to explore Mars.",
            "1.0",
            "http://terms.service.io",
            contact(),
            "Apache 2.0 license",
            "https://www.apache.org/licenses/LICENSE-2.0",
            new ArrayList<>());
        return apiInfo;
    }

    private Contact contact() {
        return new Contact("Marcos R Oliveira",
            "https://mr0ger-arduino.blogspot.com/",
            "mroger.oliveira@gmail.com");
    }
}
