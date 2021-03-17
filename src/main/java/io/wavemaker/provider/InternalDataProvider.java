package io.wavemaker.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import io.hyscale.commons.models.ImageRegistry;

@Component
@PropertySource("classpath:config/wavemaker-poc.config")
public class InternalDataProvider {

	@Value("${git.repo.destination:/tmp/cloneRepo/}")
	private String repoDestination;

	@Value("${registry.pull.url}")
	private String registryUrl;
	
	@Value("${registry.pull.auth}")
	private String registryToken;
	
	public String getRepoDestination() {
		return repoDestination;
	}

	public ImageRegistry getPullRegistry() {
		ImageRegistry pullRegistry = new ImageRegistry();
		pullRegistry.setUrl(registryUrl);
		pullRegistry.setToken(registryToken);
		return pullRegistry;
	}
}
