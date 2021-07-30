package com.example.springboot.Service;

import com.example.springboot.Entities.EventDataEntity;
import com.example.springboot.Repository.EventDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Service
public class EventDataService
{
    @Autowired
    EventDataRepository repository;

    public Page<EventDataEntity> getAllEventData(Integer pageNo, Integer pageSize, String sortBy, String startDate, String range)
    {
        Page<EventDataEntity> pagedResult;
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        if (startDate.isEmpty()) {
            pagedResult = repository.findAll(paging);
        }
        else {
            long x = Long.parseLong(startDate);
            LocalDate localDate =
                    Instant.ofEpochMilli(x)
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();

            localDate = localDate.plusDays(1);
            LocalDate rangeDate = localDate;

            switch (range) {
                case "Week":
                    rangeDate = rangeDate.plusDays(7);
                    break;
                case "Month":
                    rangeDate = rangeDate.plusMonths(1);
                    break;
                case "Quarter":
                    rangeDate = rangeDate.plusMonths(3);
                    break;
                default:
                    break;
            }

            pagedResult = repository.findByEventDateBetweenOrderByEventDateAsc(Date.valueOf(localDate), Date.valueOf(rangeDate), paging);
        }
        return pagedResult;
    }
}
