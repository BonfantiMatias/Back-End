package ar.com.argentinaprograma.apirest.models.services;

import ar.com.argentinaprograma.apirest.models.entity.Usuario;

public interface IUsuarioService {

	public Usuario findByUsername(String username);
}
