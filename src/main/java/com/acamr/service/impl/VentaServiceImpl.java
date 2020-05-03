package com.acamr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acamr.model.Venta;
import com.acamr.repo.IVentaRepo;
import com.acamr.service.IVentaService;

@Service
public class VentaServiceImpl implements IVentaService{
	
	@Autowired
	private IVentaRepo repo;

	@Override
	public Venta registrar(Venta obj) {
		obj.getDetalleVenta().forEach(det->
		det.setIdVenta(obj));
		return repo.save(obj) ;
	}

	@Override
	public Venta modificar(Venta obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Venta> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Venta listarPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

}
