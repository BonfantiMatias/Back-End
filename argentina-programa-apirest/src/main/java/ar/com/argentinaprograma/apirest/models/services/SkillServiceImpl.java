package ar.com.argentinaprograma.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.argentinaprograma.apirest.models.dao.ISkillDao;
import ar.com.argentinaprograma.apirest.models.entity.Skill;

@Service
public class SkillServiceImpl implements ISkillService {
	
	@Autowired
	private ISkillDao skillDao;
	@Override
	@Transactional(readOnly = true)
	public List<Skill> findAll() {
		
		return (List <Skill>) skillDao.findAll();
	}
	@Override
	@Transactional(readOnly = true)
	public Skill findById(Long id) {
		// TODO Auto-generated method stub
		return skillDao.findById(id).orElse(null);
	}
	@Override
	@Transactional
	public Skill save(Skill skill) {
		// TODO Auto-generated method stub
		return skillDao.save(skill);
	}
	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		skillDao.deleteById(id);
	}

}
