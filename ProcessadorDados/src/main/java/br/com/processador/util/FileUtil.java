package br.com.processador.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.google.common.io.Files;

import br.com.processador.exceptions.ArquivoException;




public class FileUtil {

	public static List<File> allFilesDat(String dir, String tipoArquivo) throws ArquivoException{
		validaStringArquivo(dir, "Diretorio do Arquivo");
		validaStringArquivo(tipoArquivo, "Tipo do Arquivo");
		List<File> arquivosProcessamento = new ArrayList<File>();
		File diretorio = new File(dir);
		File [] arquivos = diretorio.listFiles();
		for (File file : arquivos) {			
			if(Files.getFileExtension(file.getName()).equals(tipoArquivo)){
				arquivosProcessamento.add(file);
			}
		}
		return arquivosProcessamento;
	}
	
	public static void gravarAquirvo(String caminhoDiretorio, String caminhoArquivo, StringBuilder dados) throws ArquivoException {		
		validaStringArquivo(caminhoArquivo, "Caminho do arquivo");
		validaStringArquivo(dados.toString(), "Dados não encontrados");
		criaDiretorio(caminhoDiretorio);
		File arquivo = new File(caminhoArquivo);
		FileWriter gravadorArquivo;
		try {
			gravadorArquivo = new FileWriter(arquivo);
			
		    BufferedWriter s = new BufferedWriter(gravadorArquivo);
		    s.write(dados.toString());
		    s.flush();
		    s.close();
		    gravadorArquivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	private static void criaDiretorio(String caminhoDiretorio) throws ArquivoException {
		validaStringArquivo(caminhoDiretorio, "Caminho do diretorio");
		File path = new File(caminhoDiretorio);
		if(!path.exists()) {
			path.mkdirs();
		}
	}
		
	public static void modificarNomeArquivosProcessador(List<File> arquivos, String sufixoDat, String sufixoProc) {
		if(validaListaArquivos(arquivos)) {
			arquivos.forEach(
					arquivo -> {								
						try {
							renomeiaArquivo(arquivo, sufixoDat, sufixoProc);
						} catch (IOException e) {
							e.printStackTrace();
						}	
					}
				);
		}	
	}
	
	private static void renomeiaArquivo(File arquivoAtual, String sufixoDat, String sufixoProc) throws IOException {
		File arquivoRenomeacao = new File(arquivoAtual.getAbsolutePath().replace("."+sufixoDat,"."+sufixoProc));
		int count = 0;
		while(arquivoRenomeacao.exists()) {
			count++;
			String [] nome  = arquivoRenomeacao.getAbsolutePath().split(".");
			arquivoRenomeacao = new File(nome[0]+count+"."+nome[1]);
		}
		
		FileUtils.moveFile(arquivoAtual, arquivoRenomeacao);
	}
	
	private static void validaStringArquivo(String caminhoArquivo, String campoValidado) throws ArquivoException {		
		if(ValidacaoUtil.validaStringNulaVazia(caminhoArquivo)) {
			throw new ArquivoException(ValidacaoUtil.montaMensagemErroCampoNaoInformado(campoValidado)); 
		}
	}
	
	private static boolean validaListaArquivos(List<File> arquivos) {		
		return arquivos != null && !arquivos.isEmpty();
	}
	
}
