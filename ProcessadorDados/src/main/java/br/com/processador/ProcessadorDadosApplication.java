package br.com.processador;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableBatchProcessing
@EnableScheduling
@EntityScan(basePackages = "br.com")
public class ProcessadorDadosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcessadorDadosApplication.class, args);
	}

}
