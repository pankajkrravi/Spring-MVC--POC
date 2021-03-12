package com.goomo.hplus.config;

import com.goomo.hplus.convertors.StringToEnumConvertor;
import com.goomo.hplus.interceptors.LoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.format.FormatterRegistry;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.theme.CookieThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;
import org.springframework.web.servlet.view.XmlViewResolver;

import java.util.Locale;
import java.util.ResourceBundle;

@Configuration
@ComponentScan(basePackages = "com.goomo.hplus")
public class ApplicationConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
         registry.addResourceHandler("css/**","images/**")
                 .addResourceLocations("classpath:/static/css/","classpath:/static/images/");
    }
        @Bean
        public InternalResourceViewResolver jspViewResolver()
        {
            InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
            viewResolver.setPrefix("/WEB-INF/jsp/");
            viewResolver.setSuffix(".jsp");
            viewResolver.setOrder(2);
            viewResolver.setViewClass(JstlView.class);
            return  viewResolver;
        }

    @Override
    protected void addFormatters(FormatterRegistry registry) {

        registry.addConverter(new StringToEnumConvertor());
    }

    @Override
    protected void configureAsyncSupport(AsyncSupportConfigurer configurer) {
                                                    configurer.setDefaultTimeout(5000);
         configurer.setTaskExecutor(mvcTaskExecutor());
    }
      @Bean
    public AsyncTaskExecutor mvcTaskExecutor()
      {
          ThreadPoolTaskExecutor threadPoolTaskExecutor=new ThreadPoolTaskExecutor();
          threadPoolTaskExecutor.setThreadNamePrefix("HplusApp-thread-");
          return threadPoolTaskExecutor;
      }

      //XML View Resolver
    @Bean
    public XmlViewResolver viewResolver()
    {
        XmlViewResolver xmlViewResolver=new XmlViewResolver();
        xmlViewResolver.setLocation(new ClassPathResource("views.xml"));
        xmlViewResolver.setOrder(1);
        return xmlViewResolver;
    }
   /* @Bean// ResourceBundleViewResolver
    public ResourceBundleViewResolver resourceBundleViewResolver()
    {
        ResourceBundleViewResolver resolver=new ResourceBundleViewResolver();
        resolver.setBasename("views");
        return  resolver;
    }*/

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggingInterceptor()).addPathPatterns("/*");
        registry.addInterceptor(new ThemeChangeInterceptor());//theme
       // registry.addInterceptor(new LocaleChangeInterceptor());//locale
    }

    @Bean
    public ThemeResolver themeResolver()
    {
        CookieThemeResolver cookieThemeResolver=new CookieThemeResolver();
        cookieThemeResolver.setCookieName("theme");
        cookieThemeResolver.setDefaultThemeName("client-theme1");
        return cookieThemeResolver;
    }
    @Bean
    public LocaleResolver localeResolver()
    {
        CookieLocaleResolver cookieLocaleResolver=new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(Locale.US);
        cookieLocaleResolver.setCookieName("locale");
    return  cookieLocaleResolver;
    }
}
