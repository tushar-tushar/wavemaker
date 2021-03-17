package io.wavemaker.builder;

import java.util.Base64;

import org.springframework.stereotype.Component;

import io.hyscale.commons.models.ImageRegistry;

@Component
public class RegistryBuilder {

	public ImageRegistry from(String registry, String userName, char[] password) {
		ImageRegistry imageRegistry = new ImageRegistry();
		imageRegistry.setUrl(registry);
		imageRegistry.setToken(getEncodedAuth(userName, password));
		return imageRegistry;
	}
	
	private String getEncodedAuth(String userName, char[] password){
		String auth = userName + ":" + String.valueOf(password);
		
		return Base64.getEncoder().encodeToString(auth.getBytes());
	}
		
}
