package ar.com.argentinaprograma.apirest.models.services;

import java.util.List;

import ar.com.argentinaprograma.apirest.models.entity.Basico;

public interface IBasicoService {
	
	public List<Basico> findAll(); 
	
	public Basico findById(Long id);
	
	public Basico save(Basico basico);
	
	public void delete (Long id);
}
