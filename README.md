# Using Layout of Thymeleaf in WebFlux

### How to run
mvn jetty:run


### Add dependency

	&lt;!-- https://mvnrepository.com/artifact/org.thymeleaf/thymeleaf-spring6 --&gt;
	&lt;dependency&gt;
		&lt;groupId&gt;org.thymeleaf&lt;/groupId&gt;
		&lt;artifactId&gt;thymeleaf-spring6&lt;/artifactId&gt;
		&lt;version&gt;3.0.13.RELEASE&lt;/version&gt;
	&lt;/dependency&gt;


### Spring MVC Configuration

	package net.java_school.spring.config;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.context.ApplicationContext;
	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.ComponentScan;
	import org.springframework.context.annotation.Configuration;
	import org.springframework.context.support.ResourceBundleMessageSource;
	import org.springframework.web.servlet.config.annotation.EnableWebMvc;
	import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
	import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
	import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
	import org.thymeleaf.spring5.SpringTemplateEngine;
	import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
	import org.thymeleaf.spring5.view.ThymeleafViewResolver;

	@Configuration
	@EnableWebMvc
	@ComponentScan("net.java_school.blog")
	public class MvcWebConfig implements WebMvcConfigurer {

		@Autowired
		private ApplicationContext applicationContext;

		/*
		i18n
		*/
		@Bean
		public ResourceBundleMessageSource messageSource() {
			ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
			messageSource.setBasename("messages");
			messageSource.setDefaultEncoding("UTF-8");//If the encoding of the Java property file is UTF-8
			return messageSource;
		}

		/*
		Controller for static resources
		*/
		@Override
		public void addResourceHandlers(final ResourceHandlerRegistry registry) {
			registry.addResourceHandler("/static/**").addResourceLocations("/static/");
		}
		/*
		 * STEP 1 - Create SpringResourceTemplateResolver
		*/
		
		@Bean
		public SpringResourceTemplateResolver templateResolver() {
			SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
			templateResolver.setApplicationContext(applicationContext);
			templateResolver.setPrefix("/WEB-INF/views/");
			templateResolver.setSuffix(".html");
			<strong>templateResolver.setCacheable(false);</strong>//Development Mode
			return templateResolver;
		}
		/*
		 * STEP 2 - Create SpringTemplateEngine
		*/
		@Bean
		public SpringTemplateEngine templateEngine() {
			SpringTemplateEngine templateEngine = new SpringTemplateEngine();
			templateEngine.setTemplateResolver(templateResolver());
			templateEngine.setEnableSpringELCompiler(true);
			return templateEngine;
		}

		/*
		 * STEP 3 - Register ThymeleafViewResolver
		*/
		@Override
		public void configureViewResolvers(ViewResolverRegistry registry) {
			ThymeleafViewResolver resolver = new ThymeleafViewResolver();
			<strong>resolver.setCharacterEncoding("UTF-8");</strong>
			resolver.setTemplateEngine(templateEngine());
			registry.viewResolver(resolver);
		}

	}


	package net.java_school.spring.config;

	import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

	public class MvcWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

		@Override
		protected Class&lt;?&gt;[] getRootConfigClasses() {
			return null;
		}

		@Override
		protected Class&lt;?&gt;[] getServletConfigClasses() {
			return new Class[] { MvcWebConfig.class };
		}

		@Override
		protected String[] getServletMappings() {
			return new String[] { "/" };
		}

	}

