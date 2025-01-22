package com.example.rest_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rest_api.model.Client;
import com.example.rest_api.repository.ClientRepository;

@Service
public class ClientService {
	private final ClientRepository clientRepository;
	
	@Autowired
	public ClientService(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}
	
	public List<Client> getAllClients() {
		return clientRepository.findAll();
	}
	
	public Optional<Client> getClientById(Long id) {
		return clientRepository.findById(id);
	}
	
	public Optional<Client> getClientByEmail(String email) {
		return clientRepository.findByEmail(email);
	}
	
	public Client createClient(Client client) {
		return clientRepository.save(client);
	}
	
	public void deleteClient(Long id) {
		if (clientRepository.existsById(id)) {
			clientRepository.deleteById(id);
		} else {
			throw new RuntimeException("Client not found with id: " + id);
		}
	}
}
