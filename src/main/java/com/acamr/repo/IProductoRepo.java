package com.acamr.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acamr.model.Producto;

public interface IProductoRepo extends JpaRepository<Producto, Integer>{
	
}
