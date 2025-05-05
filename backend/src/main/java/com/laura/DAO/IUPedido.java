package com.laura.DAO;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.laura.model.Pedido;


public interface IUPedido extends CrudRepository<Pedido,Integer> {




}