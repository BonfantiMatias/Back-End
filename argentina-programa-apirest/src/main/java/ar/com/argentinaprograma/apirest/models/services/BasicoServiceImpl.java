package ar.com.argentinaprograma.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.argentinaprograma.apirest.models.dao.IBasicoDao;
import ar.com.argentinaprograma.apirest.models.entity.Basico;

@Service
public class BasicoServiceImpl implements IBasicoService {
	
	@Autowired
	private IBasicoDao basicoDao;
	@Override
	@Transactional(readOnly = true)
	public List<Basico> findAll() {
		
		return (List <Basico>) basicoDao.findAll();
	}
	@Override
	@Transactional(readOnly = true)
	public Basico findById(Long id) {
		// TODO Auto-generated method stub
		return basicoDao.findById(id).orElse(null);
	}
	@Override
	@Transactional
	public Basico save(Basico basico) {
		// TODO Auto-generated method stub
		return basicoDao.save(basico);
	}
	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		basicoDao.deleteById(id);
	}

}
