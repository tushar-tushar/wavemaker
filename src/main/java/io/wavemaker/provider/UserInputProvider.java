package io.wavemaker.provider;

import org.springframework.stereotype.Component;

import io.hyscale.servicespec.commons.model.service.Image;
import io.wavemaker.model.RegistryCredentials;
import io.wavemaker.model.RepositoryDetails;
import io.wavemaker.model.UserInput;

@Component
public class UserInputProvider {

	private String appName = "hyscale";

	private String serviceName = "user-service";

	private String registryUserName = "tushar0002";

	private String gitRepo = "https://github.com/tushar-tushar/user-service.git";

	private String gitUserName = "tushar-tushar";

	private String registryUrl = "registry.hub.docker.com";

	private String imageName = "tushar0002/wavemaker-user-service";

	private String imageTag = "1.0";

	public UserInput getUserInput() {
		UserInput input = new UserInput();
		input.setAppName(appName);
		input.setServiceName(serviceName);
		input.setRepoDetails(getRepoDetails());
		input.setRegistryDetails(getRegistryDetails());
		input.setImage(getImageDetails());
		return input;
	}

	public RegistryCredentials getRegistryDetails() {
		RegistryCredentials details = new RegistryCredentials();
		details.setUserName(registryUserName);
		// if encoded then decode and keep decoded password
		details.setPassword(registryPassword);
		return details;
	}

	public RepositoryDetails getRepoDetails() {
		RepositoryDetails details = new RepositoryDetails();
		details.setUrl(gitRepo);
		details.setUserName(gitUserName);
		details.setPassword(gitPassword);
		return details;
	}

	public Image getImageDetails() {
		Image image = new Image();
		image.setRegistry(registryUrl);
		image.setName(imageName);
		image.setTag(imageTag);
		return image;
	}

	private char[] registryPassword = { 'p'};
	private char[] gitPassword = {'p'};
}
