package com.example.kafkaconsumer.repos;

import com.example.kafkaconsumer.model.AccountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountDTO,Integer> {
    
}
