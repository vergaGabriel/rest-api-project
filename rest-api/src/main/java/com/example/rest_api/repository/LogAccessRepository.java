package com.example.rest_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rest_api.model.LogAccess;

@Repository
public interface LogAccessRepository extends JpaRepository<LogAccess, Long>{

}
