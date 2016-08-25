package com.xingyunzh.orderpay.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan("com.xingyunzh.orderpay")
public class WebApplicationContextConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/img/**").addResourceLocations("/WEB-INF/resources/image/");
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
//	@Override
//	public void configurePathMatch(PathMatchConfigurer configurer) {
//		UrlPathHelper urlPathHelper = new UrlPathHelper();
//		urlPathHelper.setRemoveSemicolonContent(false);
//		
//		configurer.setUrlPathHelper(urlPathHelper);
//	}
	
//	@Bean
//	public InternalResourceViewResolver getInternalResourceViewResolver(){
//		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//		resolver.setViewClass(JstlView.class);
//		resolver.setPrefix("/WEB-INF/jsp/");
//		resolver.setSuffix(".jsp");
//		
//		return resolver;
//	}

	
//	@Bean
//	public MappingJackson2JsonView jsonView(){
//		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
//		jsonView.setPrettyPrint(true);
//		
//		return jsonView;
//	}
//	
//	@Bean
//	public MarshallingView xmlView(){
//		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
//		marshaller.setClassesToBeBound(Order.class);
//		MarshallingView xmlView = new MarshallingView(marshaller);
//		return xmlView;		
//	}
	
//	@Bean 
//	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
//		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
//		resolver.setContentNegotiationManager(manager);
//		
//		ArrayList<View> views = new ArrayList<>();
//		views.add(jsonView());
//		views.add(xmlView());
//		
//		resolver.setDefaultViews(views);
//		return resolver;
//	}

}
