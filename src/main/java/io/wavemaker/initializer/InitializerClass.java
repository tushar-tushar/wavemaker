package io.wavemaker.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;

import io.wavemaker.provider.UserInputProvider;
import io.wavemaker.service.PublishService;

@SpringBootConfiguration
@ComponentScan(basePackages = { "io.wavemaker", "io.hyscale" })
public class InitializerClass implements CommandLineRunner {

	@Autowired
	private PublishService publishService;

	@Autowired
	private UserInputProvider userInputProvider;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(InitializerClass.class);
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		publishService.publish(userInputProvider.getUserInput());
	}

}
