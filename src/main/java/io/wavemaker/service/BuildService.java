package io.wavemaker.service;

import io.hyscale.servicespec.commons.model.service.Image;
import io.wavemaker.model.RegistryCredentials;

public interface BuildService {

	public void build(String repoDir, String appName, String serviceName, Image imageDetails,
			RegistryCredentials registryCredentials) throws Exception;
}
