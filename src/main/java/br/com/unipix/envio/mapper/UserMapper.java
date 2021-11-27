package br.com.unipix.envio.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.unipix.envio.model.User;
import br.com.unipix.envio.mongo.model.UsuarioMongo;

@Component
public class UserMapper {
	
	@Autowired
	private ModelMapper mapper;
	
	public UsuarioMongo modelToDtoMongo(User user) {
		return mapper.map(user, UsuarioMongo.class);
	}
}
