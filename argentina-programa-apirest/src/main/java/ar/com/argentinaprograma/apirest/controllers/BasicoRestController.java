package ar.com.argentinaprograma.apirest.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;




//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



import ar.com.argentinaprograma.apirest.models.entity.Basico;
import ar.com.argentinaprograma.apirest.models.services.IBasicoService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class BasicoRestController {
	
	@Autowired
	private IBasicoService basicoService;
	
	@GetMapping("/basicos")
	public List<Basico> index(){
		return basicoService.findAll();
	}
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/basicos/{id}")
	public Basico show(@PathVariable Long id){
	return basicoService.findById(id);	
	}
	
	@Secured({"ROLE_ADMIN"})
	@PostMapping("/basicos")
	@ResponseStatus(HttpStatus.CREATED)
	public Basico create(@RequestBody Basico basico) {
		return basicoService.save(basico);
	}
	
	@Secured({"ROLE_ADMIN"})
	@PutMapping("/basicos/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Basico update(@RequestBody Basico Basico, @PathVariable Long id) {
		Basico basicoActual = basicoService.findById(id);
		
		
		basicoActual.setNombre(Basico.getNombre());
		basicoActual.setEmail(Basico.getEmail());
		basicoActual.setNacimiento(Basico.getNacimiento());
		basicoActual.setUbicacion(Basico.getUbicacion());
		basicoActual.setIdioma(Basico.getIdioma());
		
		return basicoService.save(basicoActual);
	}
	@Secured({"ROLE_ADMIN"})
	@DeleteMapping("/basicos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		basicoService.delete(id);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PostMapping("basicos/upload")
	public ResponseEntity <?> upload(@RequestParam("archivo")MultipartFile archivo, @RequestParam("id") long id){
		Map<String, Object> response = new HashMap<>();
		
		Basico basico = basicoService.findById(id);
		
		if(!archivo.isEmpty()) {
			String nombreArchivo = UUID.randomUUID().toString()+"_"+ archivo.getOriginalFilename().replaceAll(" ","");
			Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
			
			try {
				Files.copy(archivo.getInputStream(), rutaArchivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen "+ nombreArchivo);
				response.put("error", e.getMessage().concat(":").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			String nombreFotoAnterior = basico.getFoto();
			
			if(nombreFotoAnterior !=null && nombreFotoAnterior.length() >0 ) {
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File arhivoFotoAnterior = rutaFotoAnterior.toFile();
				if(arhivoFotoAnterior.exists() && arhivoFotoAnterior.canRead()) {
					arhivoFotoAnterior.delete();
				}
			}
			
			basico.setFoto(nombreArchivo);
			
			basicoService.save(basico);
			
			response.put("basico", basico);
			response.put("mensaje", "Has subido correctamente la imagen: "+ nombreArchivo);
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
		
		Path rutaArchivo = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
		Resource recurso = null;
		
	
		try {
			recurso = (Resource) new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpHeaders cabecera= new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+recurso.getFilename()+"\"");
		
		
		return new ResponseEntity<Resource>(recurso,cabecera,HttpStatus.OK); 
	}
	
}