package com.example.rest_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rest_api.model.LogAccess;
import com.example.rest_api.repository.LogAccessRepository;

@Service
public class LogAccessService {
	private final LogAccessRepository logAccessRepository;
	
	@Autowired
	public LogAccessService(LogAccessRepository logAccessRepository) {
		this.logAccessRepository = logAccessRepository;
	}
	
	public List<LogAccess> getAllLogs() {
		return logAccessRepository.findAll();
	}
	
	public Optional<LogAccess> getLogById(Long id) {
		return logAccessRepository.findById(id);
	}
	
	public LogAccess createLog(LogAccess log) {
		return logAccessRepository.save(log);
	}
	
	public void deleteLog(Long id) {
		if (logAccessRepository.existsById(id)) {
			logAccessRepository.deleteById(id);
		} else {
			throw new RuntimeException("Log not found with id: " + id);
		}
	}
}
