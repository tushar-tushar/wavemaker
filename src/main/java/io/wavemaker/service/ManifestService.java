package io.wavemaker.service;

import java.util.List;

import io.hyscale.commons.models.Manifest;
import io.hyscale.servicespec.commons.model.service.Image;
import io.wavemaker.model.RegistryCredentials;

public interface ManifestService {

	public List<Manifest> generate(String repoDir, String appName, String serviceName, Image image,
			RegistryCredentials registryCredentials) throws Exception;
}
