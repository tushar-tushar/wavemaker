package io.wavemaker.model;

import io.hyscale.servicespec.commons.model.service.Image;

public class UserInput {

	private String serviceName;
	private String appName;
	private Image image;
	private RepositoryDetails repoDetails;
	private RegistryCredentials registryDetails;

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public RepositoryDetails getRepoDetails() {
		return repoDetails;
	}

	public void setRepoDetails(RepositoryDetails repoDetails) {
		this.repoDetails = repoDetails;
	}

	public RegistryCredentials getRegistryDetails() {
		return registryDetails;
	}

	public void setRegistryDetails(RegistryCredentials registryDetails) {
		this.registryDetails = registryDetails;
	}
}
