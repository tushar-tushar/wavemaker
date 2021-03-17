package io.wavemaker.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.hyscale.commons.models.Manifest;
import io.hyscale.commons.models.YAMLManifest;
import io.wavemaker.model.RepositoryDetails;
import io.wavemaker.model.UserInput;
import io.wavemaker.provider.InternalDataProvider;
import io.wavemaker.service.BuildService;
import io.wavemaker.service.CloneService;
import io.wavemaker.service.ManifestService;
import io.wavemaker.service.PublishService;

@Component
public class PublishServiceImpl implements PublishService {

	@Autowired
	private InternalDataProvider dataProvider;

	@Autowired
	private CloneService cloneService;

	@Autowired
	private BuildService buildService;

	@Autowired
	private ManifestService manifestService;

	@Override
	public void publish(UserInput userInput) throws Exception {
		// Clone
		RepositoryDetails repoDetails = userInput.getRepoDetails();
		cloneService.clone(repoDetails.getUrl(), repoDetails.getUserName(), repoDetails.getPassword(),
				dataProvider.getRepoDestination());

		// Build Image
		buildService.build(dataProvider.getRepoDestination(), userInput.getAppName(), userInput.getServiceName(),
				userInput.getImage(), userInput.getRegistryDetails());

		// Generate Manifest
		List<Manifest> manifestList = manifestService.generate(dataProvider.getRepoDestination(),
				userInput.getAppName(), userInput.getServiceName(), userInput.getImage(),
				userInput.getRegistryDetails());

		System.out.println("Generated Manifests");
		manifestList.forEach(each -> {
			File manifest = ((YAMLManifest) each).getManifest();
			System.out.println(manifest.getAbsolutePath());
		});
	}

}
