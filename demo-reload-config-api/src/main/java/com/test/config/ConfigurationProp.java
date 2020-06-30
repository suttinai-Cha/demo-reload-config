package com.test.config;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class ConfigurationProp {
	

	@Bean
	@ConditionalOnProperty(name = "spring.config.location", matchIfMissing = false)
	public CompositeConfiguration propertiesConfiguration(
			@Value("#{'${spring.config.location}'.split(',')}") List<String> pathList) throws Exception {

		CompositeConfiguration configuration = new CompositeConfiguration();
		pathList.forEach(path -> {
			if(path.contains("file:")) {
				String filePath = path.substring("file:".length());
				try {
					configuration.addConfiguration(new PropertiesConfiguration(new File(filePath).getCanonicalPath()));
				} catch (ConfigurationException e) {
					log.error(e.getMessage(), e);
					throw new RuntimeException(e);
				} 
				catch (IOException e) {
					log.error(e.getMessage(), e);
					throw new RuntimeException(e);
				}
			}
			else {
				try {
					configuration.addConfiguration(new PropertiesConfiguration(path));
				} catch (ConfigurationException e) {
					log.error(e.getMessage(), e);
					throw new RuntimeException(e);
				} 

			}

		})  ;
		return configuration;
	}

}
