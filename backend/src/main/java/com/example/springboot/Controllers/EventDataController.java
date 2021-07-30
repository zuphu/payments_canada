package com.example.springboot.Controllers;

import com.example.springboot.Entities.EventDataEntity;
import com.example.springboot.Service.EventDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class EventDataController {
    @Autowired
    EventDataService eventDataService;

    @GetMapping("/eventdata")
    public ResponseEntity<Page<EventDataEntity>> getData(@RequestParam(defaultValue = "0") Integer pageNo,
                                                    @RequestParam(defaultValue = "25") Integer pageSize,
                                                    @RequestParam(defaultValue = "id") String sortBy,
                                                    @RequestParam(defaultValue = "", required = false) String startDate,
                                                    @RequestParam(defaultValue = "", required = false) String range) {
        Page<EventDataEntity> eventDataList = eventDataService.getAllEventData(pageNo, pageSize, sortBy, startDate, range);
        return new ResponseEntity<>(eventDataList, new HttpHeaders(), HttpStatus.OK);
    }
}
