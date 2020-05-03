package com.acamr.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.acamr.exception.ModeloNotFoundException;
import com.acamr.model.Persona;
import com.acamr.service.IPersonaService;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/personas")
public class PersonaController {
	
	@Autowired
	private IPersonaService service;
	
	@GetMapping
	public ResponseEntity<List<Persona>>listar(){
		List<Persona>lista=service.listar();
		return new ResponseEntity<List<Persona>>(lista,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Persona>listarPorId(@PathVariable("id") Integer id){
		Persona obj=service.listarPorId(id);
		if(obj.getIdPersona()==0) {
			throw new ModeloNotFoundException("Id no encontrado : "+ id);
		}
		return new ResponseEntity<Persona>(obj,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object>registrar(@Valid @RequestBody Persona persona){
		Persona  obj=service.registrar(persona);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdPersona()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("hateoas/{id}")
	public EntityModel<Persona>listarPorIdHateoas(@PathVariable("id") Integer id){
		Persona pac=service.listarPorId(id);
		EntityModel<Persona>recurso =new EntityModel<Persona>(pac);
		WebMvcLinkBuilder linkTo=linkTo(methodOn(this.getClass()).listarPorId(id));
		recurso.add(linkTo.withRel("persona-recurso"));
		return recurso;
	}
	
	@PutMapping
	public ResponseEntity<Object>modificar(@Valid @RequestBody Persona persona){
		Persona obj=service.modificar(persona);
		return new ResponseEntity<Object>(obj,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object>eliminar(@PathVariable("id") Integer id){
		Persona obj=service.listarPorId(id);
		if(obj.getIdPersona()==0) {
			throw new ModeloNotFoundException("Id no encontrado "+id);
		}
		service.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
	
	
	
	
}
