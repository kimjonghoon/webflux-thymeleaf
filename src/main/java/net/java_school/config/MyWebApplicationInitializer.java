package net.java_school.config;

import org.springframework.web.server.adapter.AbstractReactiveWebInitializer;

public class MyWebApplicationInitializer extends AbstractReactiveWebInitializer {

	@Override
	protected Class<?>[] getConfigClasses() {
		return new Class<?>[] {AppConfig.class};
	}
}