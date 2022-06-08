package ar.com.argentinaprograma.apirest.controllers;

import java.util.List;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import ar.com.argentinaprograma.apirest.models.entity.Proyectos;

import ar.com.argentinaprograma.apirest.models.services.IProyectosService;


@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ProyectosRestController {
	
	@Autowired
	private IProyectosService proyectosService;
	
	@GetMapping("/proyectos")
	public List<Proyectos> index(){
		return proyectosService.findAll();
	}
	
	@GetMapping("/proyectos/{id}")
	public Proyectos show(@PathVariable Long id){
	return proyectosService.findById(id);	
	}
	
	@PostMapping("/proyectos")
	@ResponseStatus(HttpStatus.CREATED)
	public Proyectos create(@RequestBody Proyectos proyectos) {
		return proyectosService.save(proyectos);
	}
	
	@PutMapping("/proyectos/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Proyectos update(@RequestBody Proyectos Proyectos, @PathVariable Long id) {
		Proyectos proyectosActual = proyectosService.findById(id);
		
		
		proyectosActual.setNombre(Proyectos.getNombre());
		proyectosActual.setUrl(Proyectos.getUrl());
		proyectosActual.setGit(Proyectos.getGit());
		proyectosActual.setDescripcion(Proyectos.getDescripcion());
		
		
		
		return proyectosService.save(proyectosActual);
	}
	
	@DeleteMapping("/proyectos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		proyectosService.delete(id);
	}
	
	
	
}