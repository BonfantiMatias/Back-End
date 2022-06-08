package ar.com.argentinaprograma.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;


import ar.com.argentinaprograma.apirest.models.entity.Proyectos;


public interface IProyectosDao extends CrudRepository<Proyectos, Long> {

}
