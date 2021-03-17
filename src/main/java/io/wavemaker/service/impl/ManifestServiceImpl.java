package io.wavemaker.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.hyscale.commons.models.ImageRegistry;
import io.hyscale.commons.models.Manifest;
import io.hyscale.commons.models.ManifestContext;
import io.hyscale.generator.services.generator.ManifestGenerator;
import io.hyscale.servicespec.commons.fields.HyscaleSpecFields;
import io.hyscale.servicespec.commons.model.service.Image;
import io.hyscale.servicespec.commons.model.service.ServiceSpec;
import io.wavemaker.builder.RegistryBuilder;
import io.wavemaker.model.RegistryCredentials;
import io.wavemaker.provider.PlaceholderMapProvider;
import io.wavemaker.provider.ServiceSpecProvider;
import io.wavemaker.service.ManifestService;

@Component
public class ManifestServiceImpl implements ManifestService {

	@Autowired
	private ServiceSpecProvider serviceSpecProvider;

	@Autowired
	private ManifestGenerator manifestGenerator;

	@Autowired
	private RegistryBuilder registryBuilder;

	@Override
	public List<Manifest> generate(String repoDir, String appName, String serviceName, Image image,
			RegistryCredentials registryCredentials) throws Exception {
		Map<String, String> imageValueMap = PlaceholderMapProvider.getImagePlaceholder(image);
		ServiceSpec serviceSpec = serviceSpecProvider.getServiceSpec(repoDir, serviceName, imageValueMap);

		ManifestContext context = new ManifestContext();
		context.setAppName(appName);
		if (registryCredentials != null) {
			String registryUrl = serviceSpec
					.get(HyscaleSpecFields.getPath(HyscaleSpecFields.image, HyscaleSpecFields.registry), String.class);
			ImageRegistry imageRegistry = registryBuilder.from(registryUrl, registryCredentials.getUserName(),
					registryCredentials.getPassword());
			context.setImageRegistry(imageRegistry);
		}
		return manifestGenerator.generate(serviceSpec, context);
	}

}
