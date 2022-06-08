package ar.com.argentinaprograma.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.argentinaprograma.apirest.models.dao.IEducacionDao;

import ar.com.argentinaprograma.apirest.models.entity.Educacion;


@Service
public class EducacionServiceImpl implements IEducacionService {
	
	@Autowired
	private IEducacionDao educacionDao;
	@Override
	@Transactional(readOnly = true)
	public List<Educacion> findAll() {
		
		return (List <Educacion>) educacionDao.findAll();
	}
	@Override
	@Transactional(readOnly = true)
	public Educacion findById(Long id) {
		// TODO Auto-generated method stub
		return educacionDao.findById(id).orElse(null);
	}
	@Override
	@Transactional
	public Educacion save(Educacion educacion) {
		// TODO Auto-generated method stub
		return educacionDao.save(educacion);
	}
	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		educacionDao.deleteById(id);
	}

}
