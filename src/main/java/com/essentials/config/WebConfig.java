package com.essentials.config;

import com.essentials.serialization.converterYaml.YamlJackson2HttpMesageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.print.attribute.standard.Media;
import java.util.List;

@Configuration
public  class WebConfig implements WebMvcConfigurer {

    private static final MediaType MEDIA_TYPE_APPLICATION_YML= MediaType.valueOf("application/x-yaml");


    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
      converters.add(new YamlJackson2HttpMesageConverter());
    }


    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer){

        // Acept parameter
       // configurer.favorParameter(true)
              //  .parameterName("mediaType").ignoreAcceptHeader(true)
                //.useRegisteredExtensionsOnly(false)
               // .defaultContentType(MediaType.APPLICATION_JSON)
               // .mediaType("json",MediaType.APPLICATION_JSON)
               // .mediaType("xml",MediaType.APPLICATION_XML);


        // HEADER PARAM (Headers -> Accept  | value = application/xml OR application/json)
        configurer.favorParameter(false)
         .ignoreAcceptHeader(false)
        .useRegisteredExtensionsOnly(false)
        .defaultContentType(MediaType.APPLICATION_JSON)
        .mediaType("json",MediaType.APPLICATION_JSON)
        .mediaType("xml",MediaType.APPLICATION_XML)
        .mediaType("x-yaml",MEDIA_TYPE_APPLICATION_YML);
    }

}
