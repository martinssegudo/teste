package br.com.processador.mapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import br.com.processador.dto.LinhaDTO;

public class DtoRowMapper implements FieldSetMapper<LinhaDTO>{

	@Override
	public LinhaDTO mapFieldSet(FieldSet linha) throws BindException {
		LinhaDTO linhaDTO = new LinhaDTO();
		
		linhaDTO.setId(linha.readString("id"));
		linhaDTO.setP1(linha.readString("P1"));
		linhaDTO.setP2(linha.readString("P2"));
		linhaDTO.setP3(linha.readString("P3"));		
		
		return linhaDTO;
	}
	
	
	
}
