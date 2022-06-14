package br.rosaluz.banking.system.authentication.configuration;

import br.rosaluz.banking.system.authentication.dto.converter.UserDTOToUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new UserDTOToUser());
    }

//    @Bean
//    public WebMvcConfigurer configure() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/*").allowedOrigins("http://localhost:63342/");
//
//            }
//
//        };
    //}
}
