package spring.jpa;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer{
@Override
public void addViewControllers(ViewControllerRegistry registry) {
registry.addViewController("/login").setViewName("login");
registry.addViewController("/logout").setViewName("login");
registry.addViewController("/accueil").setViewName("accueil");
}
}