package com.example.kafkaconsumer.repository;

import com.example.kafkaconsumer.model.MessageDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<MessageDTO,Integer> {
    @Query(value = "select * from MessageDTO where id = ?1",nativeQuery = true)
    MessageDTO getMessageDTOById(Long id);
}
