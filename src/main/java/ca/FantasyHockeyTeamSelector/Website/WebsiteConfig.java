package ca.FantasyHockeyTeamSelector.Website;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class WebsiteConfig {

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("pages/");
        internalResourceViewResolver.setSuffix(".html");
        return internalResourceViewResolver;
    }

}