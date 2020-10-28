package br.com.processador.teste.configTestes;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import br.com.processador.services.ClienteService;

@Profile("TestesServices")
@Configuration
public class ConfigTestes {

	@Bean
    @Primary
    public ClienteService nameService() {
        return Mockito.mock(ClienteService.class);
    }
}
