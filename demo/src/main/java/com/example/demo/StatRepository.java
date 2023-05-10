package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StatRepository extends JpaRepository<StatDTO,Integer> {

    @Query(value = "select * from StatDTO where id = ?1",nativeQuery = true)
    StatDTO getStatDTOById(Long id);
}
