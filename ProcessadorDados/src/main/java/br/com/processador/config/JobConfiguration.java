package br.com.processador.config;


import java.io.IOException;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import br.com.processador.dto.LinhaDTO;
import br.com.processador.entidades.Cliente;
import br.com.processador.entidades.Item;
import br.com.processador.entidades.Venda;
import br.com.processador.entidades.Vendedor;
import br.com.processador.impl.MultiOutputItemWriterItem;
import br.com.processador.impl.ProcessadorCliente;
import br.com.processador.impl.ProcessadorItem;
import br.com.processador.impl.ProcessadorVenda;
import br.com.processador.impl.ProcessadorVendedor;
import br.com.processador.mapper.DtoRowMapper;

@Configuration
@EnableBatchProcessing
@EnableAutoConfiguration
public class JobConfiguration {

	private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource source;
    
    private ProcessadorCliente processadorCliente;
    private ProcessadorVendedor processadorVendedor;
    private ProcessadorVenda processadorVenda;
    private ProcessadorItem processadorItem;
    
    
    
    
    @Value("file:${data.in}")
    private String path;
    
 
    
    @Autowired
    public JobConfiguration(JobBuilderFactory jobBuilderFactory,
    						  StepBuilderFactory stepBuilderFactory,
    						  DataSource source,
    						  ProcessadorCliente processadorCliente,
    						  ProcessadorVendedor processadorVendedor,
    						  ProcessadorVenda processadorVenda,
    						  ProcessadorItem processadorItem) {
    	this.source = source;
    	this.jobBuilderFactory = jobBuilderFactory;
    	this.stepBuilderFactory = stepBuilderFactory;
    	this.processadorVendedor = processadorVendedor;
    	this.processadorVenda = processadorVenda;
    	this.processadorCliente = processadorCliente;
    	this.processadorItem = processadorItem;
    }
    
   
    @Bean
    public Job processamentoArquivo() throws Exception { 
    	return this.jobBuilderFactory.get("Processamento do Arquivo")
    			.start(stepCliente())    
    			.next(stepVenda())
    			.next(stepVendedor())
    			.next(stepItem())
    			.build();
    }
    
    
    @Bean
	public Step stepCliente() throws Exception { 
		return this.stepBuilderFactory.get("Processamento cliente")
				.<LinhaDTO, Cliente>chunk(10)
				.reader(this.multiResourceItemreader())
				.processor(processadorCliente)
				.writer(writeClientDB())
				.build();
	}
   
    
    @Bean
    public Step stepVendedor() throws Exception { 
    	return this.stepBuilderFactory.get("Processamento vendedor")
    			.<LinhaDTO,Vendedor>chunk(10)
    			.reader(this.multiResourceItemreader())
    			.processor(processadorVendedor)
    			.writer(writeVendedorDB()) 
    			.build();
    }
    
    @Bean
    public Step stepVenda() throws Exception {
    	return this.stepBuilderFactory.get("Processamento venda")
    			.<LinhaDTO,Venda>chunk(10)
    			.reader(this.multiResourceItemreader())
    			.processor(processadorVenda)
    			.writer(writeVendaDB())     		
    			.build();
    }
    
    @Bean
    public Step stepItem() throws Exception { 
    	return this.stepBuilderFactory.get("Processamento item")
    			.<LinhaDTO,Set<Item>>chunk(10)
    			.reader(this.multiResourceItemreader())
    			.processor(processadorItem)
    			.writer(writeItensDB())     		
    			.build();
    }

    
    @Bean
    @StepScope
	public MultiResourceItemReader<LinhaDTO> multiResourceItemreader() {        
    	
    	 Resource[] resources = null;
         ResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
         try {        	 
             resources = patternResolver.getResources(path+"*.dat");
         } catch (IOException e) {
             e.printStackTrace();
         }
    	
		MultiResourceItemReader<LinhaDTO> reader = new MultiResourceItemReader<LinhaDTO>();
		reader.setResources(resources);
		reader.setDelegate(readerItem());	
		return reader;
	}
    
     
    @Bean  
    @StepScope
    public FlatFileItemReader<LinhaDTO> readerItem() {
        FlatFileItemReader<LinhaDTO> reader = new FlatFileItemReader<LinhaDTO>();
        reader.setLineMapper(new DefaultLineMapper<LinhaDTO>() {
            { 
                setLineTokenizer(new DelimitedLineTokenizer("ç") {
                    { setNames(new String[]{"id", "P1", "P2", "P3"}); }
                });
                setFieldSetMapper(new DtoRowMapper());
            }
        });
        return reader;
    }
       
    
    @Bean
    public JdbcBatchItemWriter<Cliente> writeClientDB(){
    	StringBuilder sb = new StringBuilder();    	
    	sb.append("INSERT INTO TB_CLIENTE ");
    	sb.append(" (ID_CLIENTE, DS_NAME, DS_AREA_ATUACAO, DS_CNPJ ) ");
    	sb.append(" VALUES ");
    	sb.append("( ");
    	sb.append(" NEXT VALUE FOR SEQ_CLIENTE, ");
    	sb.append(":name, :busArea, :CNPJ )");
    	
    	
    	JdbcBatchItemWriter<Cliente> write = new JdbcBatchItemWriter<Cliente>();
    	write.setDataSource(source);
    	write.setSql(sb.toString());    	
    	write.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Cliente>());
    	return write;
    }   
   
    
    @Bean
    public JdbcBatchItemWriter<Vendedor> writeVendedorDB(){
    	StringBuilder sb = new StringBuilder();    	
    	sb.append("INSERT INTO TB_VENDEDOR ");
    	sb.append(" (ID_VENDEDOR, DS_NAME, DS_CPF, VL_SALARIO ) ");
    	sb.append(" VALUES ");
    	sb.append("( ");
    	sb.append(" NEXT VALUE FOR SEQ_VENDEDOR, ");
    	sb.append(":name, :CPF, :salary )");
    	
    	
    	JdbcBatchItemWriter<Vendedor> write = new JdbcBatchItemWriter<Vendedor>();
    	write.setDataSource(source);
    	write.setSql(sb.toString());    	
    	write.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Vendedor>());
    	return write;
    }  
    
    
    @Bean
    public JdbcBatchItemWriter<Venda> writeVendaDB(){
    	StringBuilder sb = new StringBuilder();    	
    	sb.append("INSERT INTO TB_VENDA ");
    	sb.append(" (ID_VENDA, DS_VENDEDOR, ID_VENDA_ARQUIVO ) ");
    	sb.append(" VALUES ");
    	sb.append("( ");
    	sb.append(" NEXT VALUE FOR SEQ_VENDEDOR, ");
    	sb.append(":vendedor, :idVenda )");
    	
    	
    	JdbcBatchItemWriter<Venda> write = new JdbcBatchItemWriter<Venda>();
    	write.setDataSource(source);
    	write.setSql(sb.toString());    	    	
    	write.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Venda>());    	
    	return write;
    }
    
       
    public ItemWriter<Set<Item>> writeItensDB(){
    	MultiOutputItemWriterItem itens = new MultiOutputItemWriterItem(source);
    	return itens;
    }
    
    
    
}
