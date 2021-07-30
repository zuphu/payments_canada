package com.example.springboot.Component;

import com.example.springboot.Entities.EventDataEntity;
import com.example.springboot.Entities.EventDetailsEntity;
import com.example.springboot.Repository.EventDataRepository;
import com.example.springboot.Repository.EventDetailsRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;


@Component
public class LoadDataFromJson {
    @Autowired
    EventDataRepository eventDataRepository;
    @Autowired
    EventDetailsRepository eventDetailsRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void LoadData() throws IOException {
        JSONParser parser = new JSONParser();
        Resource resource = new ClassPathResource("assignment_data_full.json");
        File file = resource.getFile();
        ArrayList<EventDataEntity> eventDataArray = new ArrayList<>();
        ArrayList<EventDetailsEntity> eventDetailsArray = new ArrayList<>();

        try {
            Object obj = parser.parse(new FileReader(file));
            JSONArray jsonArray = (JSONArray) obj;

            Iterator<JSONObject> iterator = jsonArray.iterator();
            long id = 0;
            while (iterator.hasNext()) {
                id++;
                JSONObject j = (JSONObject)iterator.next();
                String event_date = (String)j.get("event_date");
                String event_type = (String)j.get("event_type");
                String event_summary = (String)j.get("event_summary");
                String event_size = (String)j.get("event_size");

                String event_details = (String)j.get("event_details");

                EventDataEntity eventData = new EventDataEntity(id, Date.valueOf(event_date), event_type, event_summary, Integer.parseInt(event_size));
                eventDataArray.add(eventData);
                EventDetailsEntity eventDetails = new EventDetailsEntity(id, event_details);
                eventDetailsArray.add(eventDetails);

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        eventDataRepository.saveAll(eventDataArray);
        eventDetailsRepository.saveAll(eventDetailsArray);
    }
}
