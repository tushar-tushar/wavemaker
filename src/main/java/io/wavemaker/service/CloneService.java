package io.wavemaker.service;

public interface CloneService {

	public void clone(String repoUrl, String userName, char[] password, String destination) throws Exception;
}
