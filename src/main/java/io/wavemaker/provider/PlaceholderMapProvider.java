package io.wavemaker.provider;

import java.util.Collections;
import java.util.Map;

import io.hyscale.servicespec.commons.fields.HyscaleSpecFields;
import io.hyscale.servicespec.commons.model.service.Image;

public class PlaceholderMapProvider {

	public static Map<String, String> getImagePlaceholder(Image image) {
		if (image == null) {
			return Collections.EMPTY_MAP;
		}

		return Map.of(HyscaleSpecFields.name.toUpperCase(), image.getName(), HyscaleSpecFields.registry.toUpperCase(),
				image.getRegistry(), HyscaleSpecFields.tag.toUpperCase(), image.getTag());
	}
}
