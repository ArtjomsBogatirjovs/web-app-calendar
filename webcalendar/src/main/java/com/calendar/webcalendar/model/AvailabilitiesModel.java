package com.calendar.webcalendar.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class AvailabilitiesModel {
    // les types dependront du front ou database a voir (pour le moment string a cause du front)

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
    private LocalDate date; //LocalDate
    @Column(name = "valueStart")
    private String start; // a type for timestamp ?
    @Column(name = "valueEnd")
    private String end; // a type for timestamp ?


    public AvailabilitiesModel() {
        this.id = id;
        this.date = LocalDate.now(); // output format of LocalDate: 2021-01-03
        this.start = "08:00";
        this.end = "08:15";
    }

    public AvailabilitiesModel(Long id, LocalDate date, String start, String end) {
        this.id = id;
        this.date = date;
        this.start = start;
        this.end = end;
    }

    //one constructor without because the database will generate one
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
