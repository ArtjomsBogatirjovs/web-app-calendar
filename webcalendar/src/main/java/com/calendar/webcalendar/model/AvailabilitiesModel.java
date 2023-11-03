package com.calendar.webcalendar.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class AvailabilitiesModel {

    @Id
    @SequenceGenerator(
            name = "availabilitiesModel_sequence",
            sequenceName = "availabilitiesModel_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "availabilitiesModel_sequence"
    )
    private Long id;
    //    @Column(name = "valueDate")
    private LocalDate date;
    @Column(name = "valueStart")
    private String start;
    @Column(name = "valueEnd")
    private String end;

    public AvailabilitiesModel() {
    }

    public AvailabilitiesModel(Long id, LocalDate date, String start, String end) {
        this.id = id;
        this.date = date;
        this.start = start;
        this.end = end;
    }

    public AvailabilitiesModel(LocalDate date, String start, String end) {
        this.date = date;
        this.start = start;
        this.end = end;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "AvailabilitiesModel{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                '}';
    }
}
