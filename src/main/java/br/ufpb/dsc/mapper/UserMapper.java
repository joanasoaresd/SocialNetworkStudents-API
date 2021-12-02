package br.ufpb.dsc.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.ufpb.dsc.dto.UserDTO;
import br.ufpb.dsc.entities.User;

@Mapper
public interface UserMapper {    
	
	UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

	User toModel(UserDTO dto);
	
    @InheritInverseConfiguration
	UserDTO toDTO (User user);
    
}
