package br.ufpb.dsc.mapper;

import br.ufpb.dsc.dto.SubjectDTO;
import br.ufpb.dsc.entities.Subject;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubjectMapper {
	
	SubjectMapper MAPPER = Mappers.getMapper(SubjectMapper.class);

	Subject toModel(SubjectDTO dto);
	
    @InheritInverseConfiguration
	SubjectDTO toDTO (Subject subject);
}
