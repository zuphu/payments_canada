package com.example.springboot.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class EventDetailsEntity {
    @Id
    Long id;

    @Column
    @Lob
    String eventDetails;

    public EventDetailsEntity(Long id, String eventDetails) {
        this.id = id;
        this.eventDetails = eventDetails;
    }
}
