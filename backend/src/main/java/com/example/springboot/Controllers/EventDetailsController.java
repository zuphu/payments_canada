package com.example.springboot.Controllers;

import com.example.springboot.Entities.EventDetailsEntity;
import com.example.springboot.Repository.EventDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class EventDetailsController {
    @Autowired
    EventDetailsRepository eventDetailsRepository;

    @GetMapping("/details")
    public ResponseEntity<EventDetailsEntity> getData(@RequestParam(defaultValue = "id") String id) {
        EventDetailsEntity eventDetails = eventDetailsRepository.findById(Long.parseLong(id)).get(0);
        return new ResponseEntity<>(eventDetails, new HttpHeaders(), HttpStatus.OK);
    }
}
