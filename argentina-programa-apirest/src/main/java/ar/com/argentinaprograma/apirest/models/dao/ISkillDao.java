package ar.com.argentinaprograma.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import ar.com.argentinaprograma.apirest.models.entity.Skill;

public interface ISkillDao extends CrudRepository<Skill, Long> {

}
