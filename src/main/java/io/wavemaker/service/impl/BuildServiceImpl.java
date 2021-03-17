package io.wavemaker.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.hyscale.builder.core.models.BuildContext;
import io.hyscale.builder.services.service.ImageBuildPushService;
import io.hyscale.commons.models.ImageRegistry;
import io.hyscale.servicespec.commons.fields.HyscaleSpecFields;
import io.hyscale.servicespec.commons.model.service.Image;
import io.hyscale.servicespec.commons.model.service.ServiceSpec;
import io.wavemaker.builder.RegistryBuilder;
import io.wavemaker.model.RegistryCredentials;
import io.wavemaker.provider.InternalDataProvider;
import io.wavemaker.provider.PlaceholderMapProvider;
import io.wavemaker.provider.ServiceSpecProvider;
import io.wavemaker.service.BuildService;

@Component
public class BuildServiceImpl implements BuildService {

	@Autowired
	private RegistryBuilder registryBuilder;

	@Autowired
	private ServiceSpecProvider serviceSpecProvider;

	@Autowired
	private ImageBuildPushService imageBuildPushService;

	@Autowired
	private InternalDataProvider internalDataProvider;

	@Override
	public void build(String repoDir, String appName, String serviceName, Image imageDetails,
			RegistryCredentials registryCredentials) throws Exception {

		Map<String, String> imageValueMap = PlaceholderMapProvider.getImagePlaceholder(imageDetails);
		ServiceSpec serviceSpec = serviceSpecProvider.getServiceSpec(repoDir, serviceName, imageValueMap);

		BuildContext context = new BuildContext();
		context.setAppName(appName);
		if ("userdb".equalsIgnoreCase(serviceName)) {
			context.setStackAsServiceImage(true);
		}
		String registryUrl = serviceSpec
				.get(HyscaleSpecFields.getPath(HyscaleSpecFields.image, HyscaleSpecFields.registry), String.class);
		ImageRegistry imageRegistry = registryBuilder.from(registryUrl, registryCredentials.getUserName(),
				registryCredentials.getPassword());
		context.setPushRegistry(imageRegistry);

		context.setPullRegistry(internalDataProvider.getPullRegistry());
		imageBuildPushService.buildAndPush(serviceSpec, context);
	}

}
