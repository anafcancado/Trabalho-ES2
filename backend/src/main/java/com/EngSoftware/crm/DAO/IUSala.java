package com.EngSoftware.crm.DAO;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.EngSoftware.crm.model.Sala;


public interface IUSala extends CrudRepository<Sala,Integer> {

	Optional<Sala> findBynome(String nome);

	Optional<Sala> findByid(int id);


}