package io.wavemaker.service.impl;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.stereotype.Component;

import io.wavemaker.service.CloneService;

@Component
public class CloneServiceImpl implements CloneService {

	@Override
	public void clone(String repoUrl, String userName, char[] password, String destination) throws Exception {
		CloneCommand cloneCommand = Git.cloneRepository();
		cloneCommand.setURI(repoUrl);
		if (StringUtils.isNotBlank(userName) && password != null) {
			cloneCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider(userName, password));
		}
		File directory = new File(destination);
		if (!directory.exists()) {
			System.out.println("Repository doesnot exist, creating:: " + directory.getAbsolutePath());
			directory.mkdirs();
		} else {
			System.out.println("Directory exists, cleaning:: " + directory.getAbsolutePath());
			FileUtils.cleanDirectory(directory);
		}
		cloneCommand.setDirectory(directory);
		Git git = cloneCommand.call();
		git.close();
		System.out.println("Git repo clonned in " + directory.getAbsolutePath());
	}

}
