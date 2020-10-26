package br.com.processador.config;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import br.com.processador.rservices.ClienteService;
import br.com.processador.rservices.ItemService;
import br.com.processador.rservices.VendaService;
import br.com.processador.rservices.VendedorService;
import br.com.processador.util.FileUtil;

@Configuration
public class ScheduledConfig {

	@Value("${data.in}")
	private String pathDataIn;
	
	@Value("${data.out}")
	private String pathDataOut;
	
	@Value("${file.in.sufix}")
	private String fileDataInSufix;
	
	@Value("${file.out.sufix}")
	private String fileDataOutSufix;
	
	@Value("${file.proc.sufix}")
	private String fileDataProcSufix;
	
	private JobConfiguration job;
	
	private JobRunner lauch;
	
	private ClienteService clienteService;
	private ItemService itemService;
	private VendaService vendaService;
	private VendedorService vendedorService;
	
	@Autowired
	public ScheduledConfig(JobConfiguration job,
						   JobRunner lauch,
						   ClienteService clienteService,
						   ItemService itemService,
						   VendaService vendaService,
						   VendedorService vendedorService) {
		this.job = job;
		this.lauch = lauch;
		this.clienteService = clienteService;
		this.itemService = itemService;
		this.vendaService = vendaService;
		this.vendedorService = vendedorService;
	}
	
	
	
	@Scheduled(fixedDelay = 60000)
	public void teste() throws Exception{		
		//this.limpaBanco();
		List<File> arquivos = FileUtil.allFilesDat(pathDataIn, fileDataInSufix);
		
		
		lauch.runBatchJob();
	    	
		FileUtil.gravarAquirvo(fileDataOutSufix,pathDataOut+new Date().getTime()+"."+fileDataOutSufix, dadosGravacao());	    			
		FileUtil.modificarNomeArquivosProcessador(arquivos, fileDataInSufix, fileDataProcSufix);
		
	}
	
	private StringBuilder dadosGravacao() {
		StringBuilder sb = new StringBuilder();
		sb.append(clienteService.contagemClientes()+",");
		sb.append(vendedorService.contagemVendedores()+",");
		sb.append(vendaService.getIdMaiorVenda()+",");
		sb.append(vendedorService.getPiroVendedor()+",");
		return sb;
	}
	
	private void limpaBanco() {
		clienteService.revomeAll();
		itemService.revomeAll();
		vendaService.revomeAll();
		vendedorService.revomeAll();
	}
}
