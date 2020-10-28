package br.com.processador.test.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Test;

import br.com.processador.exceptions.ArquivoException;
import br.com.processador.util.FileUtil;
import br.com.processador.util.ValidacaoUtil;

class FileUtilTestCase {

	
	@Test
	void test() {
		//fail("Not yet implemented");
		assertTrue(true);
	}

	@Test
	void diretorioNaoInformadosEmAllFilesDat(){
		try {
			FileUtil.allFilesDat(null, ".dat");
		}catch (ArquivoException e) {
			assertEquals(ValidacaoUtil.montaMensagemErroCampoNaoInformado("Diretorio do Arquivo"), e.menssagem);
		}		
	}
	
	@Test
	void tipoArquivoNaoInformadosEmAllFilesDat(){
		try {
			FileUtil.allFilesDat("teste", null);
		}catch (ArquivoException e) {
			assertEquals(ValidacaoUtil.montaMensagemErroCampoNaoInformado("Tipo do Arquivo"), e.menssagem);
		}		
	}
	
	@Test
	void gravaArquivoSemCaminhoDiretoriogravarAquirvo() {
		try {
			FileUtil.gravarAquirvo(null, "TESTE", new StringBuilder("DADOS"));
		} catch (ArquivoException e) {
			assertEquals(ValidacaoUtil.montaMensagemErroCampoNaoInformado("Caminho do diretorio"), e.menssagem);
		}
	}
	
	@Test
	void gravaArquivoSemCaminhoArquivogravarAquirvo() {
		try {
			FileUtil.gravarAquirvo("", null, null);
		} catch (ArquivoException e) {
			assertEquals(ValidacaoUtil.montaMensagemErroCampoNaoInformado("Caminho do arquivo"), e.menssagem);
		}
	}
	
	@Test
	void gravaArquivoSemDadosgravarAquirvo() {
		try {
			FileUtil.gravarAquirvo("TESTE", "TESTE", new StringBuilder("DADOS"));
		} catch (ArquivoException e) {
			assertEquals(ValidacaoUtil.montaMensagemErroCampoNaoInformado("Dados não encontrados"), e.menssagem);
		}
	}
}
