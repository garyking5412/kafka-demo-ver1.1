package com.example.kafkaproducer.repository;

import com.example.kafkaproducer.model.MessageDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageDTO, Integer> {
    List<MessageDTO> findByStatus(boolean status);
}
