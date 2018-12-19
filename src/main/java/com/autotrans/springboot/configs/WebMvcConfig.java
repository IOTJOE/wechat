package com.autotrans.springboot.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 自定义WEB拦截器
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    
    private static final String encoding = "UTF-8";
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/views/");
//        super.addResourceHandlers(registry);
//    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/dist/index.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    } 
//    @Bean
//    public HttpMessageConverters customConverters() {
//        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(
//                Charset.forName(encoding)); // 解决直接string返回乱码问题
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//        List<MediaType> types = new LinkedList<>();
//        types.add(MediaType.ALL);
//        converter.setSupportedMediaTypes(types);
//        converter.setDefaultCharset(Charset.forName(encoding));
//        return new HttpMessageConverters(converter, stringHttpMessageConverter);
//    }

@Override
public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
            .allowedOrigins("*")
            .allowCredentials(true)
            .allowedMethods("GET", "POST", "DELETE", "PUT")
            .maxAge(3600);
}

}
