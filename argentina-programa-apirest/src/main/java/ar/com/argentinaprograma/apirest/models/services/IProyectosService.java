package ar.com.argentinaprograma.apirest.models.services;

import java.util.List;


import ar.com.argentinaprograma.apirest.models.entity.Proyectos;


public interface IProyectosService {
	
	public List<Proyectos> findAll(); 
	
	public Proyectos findById(Long id);
	
	public Proyectos save(Proyectos proyectos);
	
	public void delete (Long id);
}
