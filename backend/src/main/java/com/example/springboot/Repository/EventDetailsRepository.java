package com.example.springboot.Repository;

import com.example.springboot.Entities.EventDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EventDetailsRepository extends JpaRepository<EventDetailsEntity, Long> {
    List<EventDetailsEntity> findById(long id);
}
