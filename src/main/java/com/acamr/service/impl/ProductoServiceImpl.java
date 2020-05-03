package com.acamr.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acamr.model.Producto;
import com.acamr.repo.IProductoRepo;
import com.acamr.service.IProductoService;

@Service
public class ProductoServiceImpl implements IProductoService {

	@Autowired
	private IProductoRepo repo;

	@Override
	public Producto registrar(Producto obj) {
		// TODO Auto-generated method stub
		return repo.save(obj);
	}

	@Override
	public Producto modificar(Producto obj) {
		// TODO Auto-generated method stub
		return repo.save(obj);
	}

	@Override
	public List<Producto> listar() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Producto listarPorId(Integer id) {
		Optional<Producto> op = repo.findById(id);
		return op.isPresent() ? op.get() : new Producto();
	}

	@Override
	public boolean eliminar(Integer id) {
		repo.deleteById(id);
		return true;
	}

}
