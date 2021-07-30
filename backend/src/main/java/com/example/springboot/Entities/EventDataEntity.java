package com.example.springboot.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.Date;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class EventDataEntity {
    @Id
    Long id;

    @Column
    Date eventDate;
    @Column
    String eventType;
    @Column
    String eventSummary;
    @Column
    int eventSize;

    public EventDataEntity(long id, Date eventDate, String eventType, String eventSummary, int eventSize) {
        this.id = id;
        this.eventDate = eventDate;
        this.eventType = eventType;
        this.eventSummary = eventSummary;
        this.eventSize = eventSize;
    }
}
