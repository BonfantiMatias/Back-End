package ar.com.argentinaprograma.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import ar.com.argentinaprograma.apirest.models.entity.Basico;

public interface IBasicoDao extends CrudRepository<Basico, Long> {

}
