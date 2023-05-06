package com.example.kafkaproducer.repository;

import com.example.kafkaproducer.model.StatDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatRepository extends JpaRepository<StatDTO, Integer> {
    List<StatDTO> findByStatus(boolean status);
}
