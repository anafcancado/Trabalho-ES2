package com.laura.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laura.DAO.IUsuario;
import com.laura.model.Usuario;



@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UsuarioController  {
	@Autowired
	private IUsuario dao;
	
	// ***************POST /users: Registrar um novo usuário.********************
	@PostMapping
	public ResponseEntity<String> criarUsuario(@RequestBody Usuario usuario){
		
		if(usuarioJaExiste(usuario.getUsername())){
			 return ResponseEntity.status(HttpStatus.CONFLICT).body("Nome de usuario indisponivel");
		}
		else {
			dao.save(usuario);
			return ResponseEntity.status(HttpStatus.CREATED).body("Usuario cadastrado com sucesso");
		}
	}
	
	//********** POST /users/login: Autenticar um usuário **************
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Usuario usuario) {
	    
	    if (autenticar(usuario)) {
	        // Autenticação bem-sucedida
	        return ResponseEntity.ok("Autorizado");
	    } else {
	        // Autenticação falhou
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Não autorizado");
	    }
	}

	//********** GET /users/{username}: Obter informações de um usuário específico **************
	@GetMapping("/{username}")
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable String username) {
	    java.util.Optional<Usuario> usuario = dao.findByUsername(username);
	    if (usuario.isPresent()) {
	        return ResponseEntity.ok(usuario.get());
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	//********** GET /users : lista todos os usuarios **************
	@GetMapping
	public List <Usuario> listaUsuarios() {
		return (List<Usuario>)dao.findAll();
	}
	
	
	
	private boolean usuarioJaExiste(String username) {
		boolean existe=false;
	    List<Usuario> aux=listaUsuarios();
	    for(int i=0;i<aux.size();i++) {
	    	if(aux.get(i).getUsername().equals(username)) {
	    		existe=true;
	    		break;
	    	}
	    }
	    return existe; 
	}
	
	private boolean autenticar(Usuario usuario) {
		boolean sucesso=false;
	    List<Usuario> aux=listaUsuarios();
	    String senhaDigitada = usuario.getSenha();
	    for(int i=0;i<aux.size();i++) {
	    	if(aux.get(i).getUsername().equals(usuario.getUsername())&&aux.get(i).getSenha().equals(senhaDigitada)) {
	    		sucesso=true;
	    		break;
	    	}
	    }
	    return sucesso; 
	}
	
	

}
