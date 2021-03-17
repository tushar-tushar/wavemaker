package io.wavemaker.service;

import io.wavemaker.model.UserInput;

public interface PublishService {

	public void publish(UserInput userInput) throws Exception;
}
