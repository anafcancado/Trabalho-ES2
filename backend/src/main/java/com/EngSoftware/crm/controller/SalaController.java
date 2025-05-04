package com.EngSoftware.crm.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
@RequestMapping("/rooms")
@CrossOrigin("*")

public class SalaController  {
	@Autowired
	private IUSala dao;
	@Autowired
	private IUsuario dao2;
	@Autowired
	private IUMensagem dao3;
	
	
	//********** POST /rooms: Criar uma nova sala de chat.**************
	@PostMapping
	public ResponseEntity<Sala> criarSala(@RequestBody Sala sala){
		
		Sala salaNova=dao.save(sala);
		return ResponseEntity.status(HttpStatus.CREATED).body(salaNova);
		
	}
	
	//********** DELETE /rooms/{roomId}: Remover uma sala de chat.**************
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletarSala(@PathVariable int id) {
		
	    Optional<Sala> salaOptional = dao.findByid(id);

	    if (salaOptional.isPresent()) {
	        dao.delete(salaOptional.get());
	        return ResponseEntity.ok("Sala excluida");
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	//********** POST /rooms/{roomId}/enter **************
	@PostMapping("/{roomId}/enter")
	public ResponseEntity<String> entrarSala(
			
        @PathVariable Integer roomId,
        @RequestHeader("X-User-Id") Integer userId) {
		

	    Optional<Sala> salaOptional = dao.findById(roomId);
	    Optional<Usuario> usuarioOptional = dao2.findById(userId);

	    if (salaOptional.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sala não encontrada.");
	    }

	    if (usuarioOptional.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
	    }

	    Sala sala = salaOptional.get();
	    Usuario usuario = usuarioOptional.get();

	    if (!sala.getUsuarios().contains(usuario)) {
	        sala.getUsuarios().add(usuario);
	        dao.save(sala);
	    }

	    return ResponseEntity.ok("Usuário entrou na sala com sucesso.");
	}
	
	//********** POST /rooms/{roomId}/leave **************
	@PostMapping("/{roomId}/leave")
	public ResponseEntity<String> sairSala(
	        @PathVariable Integer roomId,
	        @RequestHeader("X-User-Id") Integer userId) {

	    Optional<Sala> salaOptional = dao.findById(roomId);
	    Optional<Usuario> usuarioOptional = dao2.findById(userId);

	    if (salaOptional.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sala não encontrada.");
	    }

	    if (usuarioOptional.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
	    }

	    Sala sala = salaOptional.get();
	    Usuario usuario = usuarioOptional.get();

	    if (sala.getUsuarios().contains(usuario)) {
	        sala.getUsuarios().remove(usuario);
	        dao.save(sala);
	        return ResponseEntity.ok("Usuário saiu da sala com sucesso.");
	    } else {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não está na sala.");
	    }
	}
	//********** DELETE /rooms/{roomId}/users/{userId}: Remover um usuario x de uma sala y**************
	@DeleteMapping("/{roomId}/users/{userId}")
	public ResponseEntity<String> removerUsuarioDaSala(
	        @PathVariable Integer roomId,
	        @PathVariable Integer userId) {

	    Optional<Sala> salaOptional = dao.findById(roomId);
	    Optional<Usuario> usuarioOptional = dao2.findById(userId);

	    if (salaOptional.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sala não encontrada.");
	    }

	    if (usuarioOptional.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
	    }

	    Sala sala = salaOptional.get();
	    Usuario usuario = usuarioOptional.get();

	    if (sala.getUsuarios().contains(usuario)) {
	        sala.getUsuarios().remove(usuario);
	        dao.save(sala);
	        return ResponseEntity.ok("Usuário removido da sala com sucesso.");
	    } else {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não está na sala.");
	    }
	}
	
	//********** POST /rooms/{roomId}/messages: enviar mensagem para uma sala*************
	@PostMapping("/{roomId}/messages")
	public ResponseEntity<String> enviarMensagemSala(
	        @PathVariable Integer roomId,
	        @RequestHeader("X-User-Id") Integer senderId,
	        @RequestBody Map<String, String> body) {

	    String conteudo = body.get("conteudo");

	    Optional<Usuario> usuarioOpt = dao2.findById(senderId);
	    Optional<Sala> salaOpt = dao.findById(roomId);

	    if (usuarioOpt.isEmpty() || salaOpt.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário ou sala não encontrado.");
	    }

	    Mensagem mensagem = new Mensagem();
	    mensagem.setConteudo(conteudo);
	    mensagem.setRemetente(usuarioOpt.get());
	    mensagem.setSala(salaOpt.get());
	    mensagem.setDataHora(LocalDateTime.now());
	    mensagem.setDestinatario(null); // importante

	    dao3.save(mensagem);

	    return ResponseEntity.status(HttpStatus.CREATED).body("Mensagem enviada para a sala.");
	}
	
	//********** GET /rooms/{roomId}/messages: Receber mensagens de uma sala de chat.*************
	@GetMapping("/{roomId}/messages")
	public ResponseEntity<List<Mensagem>> listarMensagensSala(@PathVariable Integer roomId) {
	    if (!dao.existsById(roomId)) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    }

	    List<Mensagem> mensagens = dao3.findBySalaIdAndDestinatarioIsNullOrderByDataHoraAsc(roomId);
	    return ResponseEntity.ok(mensagens);
	}
	

	@GetMapping
	public List <Sala> listaSalas() {
		return (List<Sala>)dao.findAll();
	}
	
	@GetMapping("/{nome}")
	public ResponseEntity<Sala> getSalaBynome(@PathVariable String nome) {
	    java.util.Optional<Sala> sala = dao.findBynome(nome);
	    if (sala.isPresent()) {
	        return ResponseEntity.ok(sala.get());
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	




}
