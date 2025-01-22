package com.example.rest_api.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.rest_api.model.Client;
import com.example.rest_api.model.LogAccess;
import com.example.rest_api.service.ClientService;
import com.example.rest_api.service.LogAccessService;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private LogAccessService logAccessService;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@GetMapping
	public List<Client> getAllClients() {
		return clientService.getAllClients();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Client> getBookById(@PathVariable Long id) {
		Optional<Client> client = clientService.getClientById(id);
        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
	
	@PostMapping
	public Client createClient(@RequestBody Client client) {
		client.setPassword(passwordEncoder.encode(client.getPassword()));
		return clientService.createClient(client);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
		clientService.deleteClient(id);
		return ResponseEntity.noContent().build();	
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Client client) {
		Optional<Client> existingClient = clientService.getClientByEmail(client.getEmail());
	    
	    System.out.println(existingClient.get());
	    if (existingClient.isPresent()) {
	        if (passwordEncoder.matches(client.getPassword(), existingClient.get().getPassword())) {
	        	
	        	
	        	LogAccess log = new LogAccess();
	        	log.setClientId(existingClient.get());
	        	log.setLoginDate(LocalDateTime.now());
	        	
	        	logAccessService.createLog(log);
	        	
	            return ResponseEntity.ok().body("Login bem-sucedido");
	        }
	    }
	    return ResponseEntity.status(401).body("Credenciais inválidas");
	}
}
