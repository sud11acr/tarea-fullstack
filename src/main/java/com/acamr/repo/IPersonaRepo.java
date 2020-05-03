package com.acamr.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acamr.model.Persona;

public interface IPersonaRepo extends JpaRepository<Persona, Integer>{
	
}
