package com.EngSoftware.crm.controller;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EngSoftware.crm.DAO.IUMensagem;
import com.EngSoftware.crm.DAO.IUSala;
import com.EngSoftware.crm.DAO.IUsuario;
import com.EngSoftware.crm.model.Mensagem;
import com.EngSoftware.crm.model.Sala;
import com.EngSoftware.crm.model.Usuario;

@RestController
@RequestMapping("/messages")
@CrossOrigin("*")

public class MensagemController {
	
	@Autowired
	private IUMensagem dao;
	@Autowired
	private IUsuario dao2;
	@Autowired
	private IUSala dao3;
	
	//**************** POST /messages/direct/{receiverId}: Enviar uma mensagem direta para outro usuário. *****************
	@PostMapping("/direct/{receiverId}")
	public ResponseEntity<String> enviarMensagemDireta(
	        @RequestHeader("X-User-Id") Integer senderId,
	        @PathVariable Integer receiverId,
	        @RequestBody Map<String, Object> body) {

	    String conteudo = (String) body.get("conteudo");
	    Integer salaId = body.get("salaId") != null ? (Integer) body.get("salaId") : null;

	    Optional<Usuario> remetenteOpt = dao2.findById(senderId);
	    Optional<Usuario> destinatarioOpt = dao2.findById(receiverId);

	    if (remetenteOpt.isEmpty() || destinatarioOpt.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
	    }

	    Sala sala = null;
	    if (salaId != null) {
	        sala = dao3.findById(salaId).orElse(null);
	        if (sala == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sala informada não existe.");
	        }
	    }

	    Mensagem mensagem = new Mensagem();
	    mensagem.setConteudo(conteudo);
	    mensagem.setRemetente(remetenteOpt.get());
	    mensagem.setDestinatario(destinatarioOpt.get());
	    mensagem.setSala(sala);
	    mensagem.setDataHora(LocalDateTime.now());

	    dao.save(mensagem);

	    return ResponseEntity.status(HttpStatus.CREATED).body("Mensagem direta enviada com sucesso.");
	}

}
