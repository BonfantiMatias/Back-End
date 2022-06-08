package ar.com.argentinaprograma.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import ar.com.argentinaprograma.apirest.models.dao.IProyectosDao;

import ar.com.argentinaprograma.apirest.models.entity.Proyectos;


@Service
public class ProyectosServiceImpl implements IProyectosService {
	
	@Autowired
	private IProyectosDao proyectosDao;
	@Override
	@Transactional(readOnly = true)
	public List<Proyectos> findAll() {
		
		return (List <Proyectos>) proyectosDao.findAll();
	}
	@Override
	@Transactional(readOnly = true)
	public Proyectos findById(Long id) {
		// TODO Auto-generated method stub
		return proyectosDao.findById(id).orElse(null);
	}
	@Override
	@Transactional
	public Proyectos save(Proyectos proyectos) {
		// TODO Auto-generated method stub
		return proyectosDao.save(proyectos);
	}
	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		proyectosDao.deleteById(id);
	}

}
