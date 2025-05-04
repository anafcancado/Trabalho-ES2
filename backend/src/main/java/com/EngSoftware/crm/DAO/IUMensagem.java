package com.EngSoftware.crm.DAO;


import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.EngSoftware.crm.model.Mensagem;


public interface IUMensagem extends CrudRepository<Mensagem,Integer> {

	List<Mensagem> findBySalaIdAndDestinatarioIsNullOrderByDataHoraAsc(Integer roomId);


	

}