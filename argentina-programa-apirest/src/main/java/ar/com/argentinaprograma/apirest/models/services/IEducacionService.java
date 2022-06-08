package ar.com.argentinaprograma.apirest.models.services;

import java.util.List;

import ar.com.argentinaprograma.apirest.models.entity.Educacion;


public interface IEducacionService {
	
	public List<Educacion> findAll(); 
	
	public Educacion findById(Long id);
	
	public Educacion save(Educacion educacion);
	
	public void delete (Long id);
}
