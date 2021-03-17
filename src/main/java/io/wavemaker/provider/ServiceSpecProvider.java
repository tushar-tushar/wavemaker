package io.wavemaker.provider;

import java.io.File;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.stereotype.Component;

import io.hyscale.commons.config.SetupConfig;
import io.hyscale.commons.io.HyscaleFilesUtil;
import io.hyscale.commons.utils.ObjectMapperFactory;
import io.hyscale.servicespec.commons.model.service.ServiceSpec;

@Component
public class ServiceSpecProvider {

	public ServiceSpec getServiceSpec(String directory, String serviceName, Map<String, String> placeHolders)
			throws Exception {
		StringBuilder sb = new StringBuilder(directory);
		sb.append(File.separator).append(serviceName).append(".hspec");
		File serviceSpecFile = new File(sb.toString());
		if (!serviceSpecFile.exists() || !serviceSpecFile.isFile()) {
			throw new IllegalArgumentException(
					"ServiceSpec not found at location:: " + serviceSpecFile.getAbsolutePath());
		}
		SetupConfig.setAbsolutePath(serviceSpecFile.getParent());
		String serviceSpecData = HyscaleFilesUtil.readFileData(new File(serviceSpecFile.getAbsolutePath()));
		serviceSpecData = resolvePlaceHolders(serviceSpecData, placeHolders);
		return new ServiceSpec(ObjectMapperFactory.yamlMapper().readTree(serviceSpecData));
	}

	public String resolvePlaceHolders(String serviceSpec, Map<String, String> valueMap) {
		if (StringUtils.isBlank(serviceSpec) || valueMap == null || valueMap.isEmpty()) {
			return serviceSpec;
		}
		StringSubstitutor substitutor = new StringSubstitutor(valueMap);
		return substitutor.replace(serviceSpec);
	}
}
