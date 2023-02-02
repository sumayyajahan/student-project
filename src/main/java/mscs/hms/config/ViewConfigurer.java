package mscs.hms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ViewConfigurer implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //ViewConfigurer.super.addViewControllers(registry);
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/users").setViewName("user_list");
        registry.addViewController("/register").setViewName("signup");
        registry.addViewController("/register_success").setViewName("register_success");
        registry.addViewController("/property_list").setViewName("property_list");
        registry.addViewController("/company_list").setViewName("company_list");        
        registry.addViewController("/company_edit").setViewName("company_edit");
        registry.addViewController("/house_list").setViewName("house_list");
        registry.addViewController("/house_edit").setViewName("house_edit");
        registry.addViewController("/apartment_list").setViewName("apartment_list");
        registry.addViewController("/apartment_edit").setViewName("apartment_edit");
    }
}
