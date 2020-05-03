package com.acamr.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acamr.model.Venta;

public interface IVentaRepo extends JpaRepository<Venta, Integer>{

}
