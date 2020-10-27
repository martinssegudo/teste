package br.com.processador.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.google.common.io.Files;




public class FileUtil {

	public static List<File> allFilesDat(String dir, String tipo){
		List<File> arquivosProcessamento = new ArrayList<File>();
		File diretorio = new File(dir);
		File [] arquivos = diretorio.listFiles();
		for (File file : arquivos) {			
			if(Files.getFileExtension(file.getName()).equals(tipo)){
				arquivosProcessamento.add(file);
			}
		}
		return arquivosProcessamento;
	}
	
	
	public static void gravarAquirvo(String caminho, String caminhoArquivo, StringBuilder dados) {
		File path = new File(caminho);
		if(!path.exists()) {
			path.mkdirs();
		}
		File arquivo = new File(caminhoArquivo);
		FileWriter gravar;
		try {
			gravar = new FileWriter(arquivo);
			
		    BufferedWriter s = new BufferedWriter(gravar);
		    s.write(dados.toString());
		    s.flush();
		    s.close();
		    gravar.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	
	public static void modificarNomeArquivosProcessador(List<File> arquivos, String sufixoDat, String sufixoProc) {
		if(arquivos != null
				&& !arquivos.isEmpty()) {
			arquivos.forEach(
					arquivo -> {								
						try {
							File mov = new File(arquivo.getAbsolutePath().replace("."+sufixoDat,"."+sufixoProc));
							int count = 0;
							while(mov.exists()) {
								count++;
								String [] nome  = mov.getAbsolutePath().split(".");
								mov = new File(nome[0]+count+"."+nome[1]);
							}
							
							FileUtils.moveFile(arquivo, mov);
						} catch (IOException e) {
							e.printStackTrace();
						}	
					}
				);
		}
		
	}
}
