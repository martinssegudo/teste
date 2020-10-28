package br.com.processador.teste.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.processador.ProcessadorDadosApplication;
import br.com.processador.services.ClienteService;


@ActiveProfiles("TestesServices")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProcessadorDadosApplication.class)
class ClienteSeviceTestCase {

	@Autowired
	private ClienteService clienteService;
	
	@Test
	void contagemClientesRetornandoNada() {
		Mockito.when(clienteService.contagemClientes()).thenReturn(0L);
		assertEquals(clienteService.contagemClientes(),0l);
	}

}
