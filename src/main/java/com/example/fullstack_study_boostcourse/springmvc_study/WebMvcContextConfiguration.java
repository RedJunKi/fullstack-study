package com.example.fullstack_study_boostcourse.springmvc_study;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
//@ComponentScan(basePackages = "com.example.fullstack_study_boostcourse.springmvc_study.controller")
public class WebMvcContextConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/")
                .setCachePeriod(31556926);
        registry.addResourceHandler("/css/**")
                .addResourceLocations("/css/")
                .setCachePeriod(31556926);
        registry.addResourceHandler("/img/**")
                .addResourceLocations("/img/")
                .setCachePeriod(31556926);
        registry.addResourceHandler("/js/**")
                .addResourceLocations("/js/")
                .setCachePeriod(31556926);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        System.out.println("addViewControllers가 호출됩니다.");
        registry.addViewController("/").setViewName("main");
    }

    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
}
