package com.acamr.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acamr.model.Persona;
import com.acamr.repo.IPersonaRepo;
import com.acamr.service.IPersonaService;

@Service
public class PersonaServiceImpl implements IPersonaService {

	@Autowired
	private IPersonaRepo repo;

	@Override
	public Persona registrar(Persona obj) {
		// TODO Auto-generated method stub
		return repo.save(obj);
	}

	@Override
	public Persona modificar(Persona obj) {
		// TODO Auto-generated method stub
		return repo.save(obj);
	}

	@Override
	public List<Persona> listar() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Persona listarPorId(Integer id) {
		Optional<Persona> op = repo.findById(id);
		return op.isPresent() ? op.get() : new Persona();
	}

	@Override
	public boolean eliminar(Integer id) {
		repo.deleteById(id);
		return true;
	}

}
