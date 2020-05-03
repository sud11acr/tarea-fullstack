package com.acamr.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.acamr.model.Producto;
import com.acamr.service.IProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	private IProductoService service;
	
	@GetMapping
	public ResponseEntity<List<Producto>>listar(){
		List<Producto>lista=service.listar();
		return new  ResponseEntity<List<Producto>>(lista,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Producto>listarPorId(@PathVariable Integer id){
	   Producto obj=service.listarPorId(id);
	   if(obj.getIdProducto()==0) {
		   throw  new ModeloNotFoundException("Id no encontrado "+id );
	   }
	   return new ResponseEntity<Producto>(obj,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object>registrar(@Valid @RequestBody Producto producto){
		Producto obj=service.registrar(producto);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdProducto()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Object>modificar(@Valid @RequestBody Producto producto){
		Producto obj=service.modificar(producto);
		return new ResponseEntity<Object>(obj,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object>eliminar(@PathVariable Integer id){
		Producto obj=service.listarPorId(id);
		if(obj.getIdProducto()==0) {
			throw new ModeloNotFoundException("Id no encontrado "+id);
		}
		service.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
	
}
