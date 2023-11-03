package com.calendar.webcalendar.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class ReservationsModel {

    @Id
    @SequenceGenerator(
            name = "reservationsModel_sequence",
            sequenceName = "reservationsModel_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "reservationsModel_sequence"
    )

    private Long id;
    private LocalDate date;
    @Column(name = "valueStart")
    private String start;
    @Column(name = "valueEnd")
    private String end;
    @Column(name = "valueTitle")
    private String title;
    @Column(name = "valueEmail")
    private String email;

    @Column(name = "player_one", nullable = false)
    private String playerOne;

    @Column(name = "player_two", nullable = false)
    private String playerTwo;

    public String getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(String playerTwo) {
        this.playerTwo = playerTwo;
    }

    public String getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(String playerOne) {
        this.playerOne = playerOne;
    }

    public ReservationsModel() {
    }

    public ReservationsModel(Long id, LocalDate date, String start, String end, String title, String email) {
        this.id = id;
        this.date = date;
        this.start = start;
        this.end = end;
        this.title = title;
        this.email = email;
    }

    //one constructor without because the database will generate one
    public ReservationsModel(LocalDate date, String start, String end, String title, String email) {
        this.date = date;
        this.start = start;
        this.end = end;
        this.title = title;
        this.email = email;
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

    public String getTitle() {
        return title;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "reservationsModel{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", title='" + title + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
