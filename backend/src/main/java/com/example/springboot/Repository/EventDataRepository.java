package com.example.springboot.Repository;
import com.example.springboot.Entities.EventDataEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Pageable;

import java.sql.Date;

public interface EventDataRepository extends PagingAndSortingRepository<EventDataEntity, Long> {
    Page<EventDataEntity> findByEventDateBetweenOrderByEventDateAsc(Date startDate, Date endDate, Pageable pageable);
}
