package com.EngSoftware.crm.DAO;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.EngSoftware.crm.model.Usuario;


public interface IUsuario extends CrudRepository<Usuario,Integer> {
	Optional<Usuario> findByUsername(String username);

}
