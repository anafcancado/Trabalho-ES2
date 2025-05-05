package com.laura.DAO;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.laura.model.Usuario;


public interface IUsuario extends CrudRepository<Usuario,Integer> {
	Optional<Usuario> findByUsername(String username);


}
