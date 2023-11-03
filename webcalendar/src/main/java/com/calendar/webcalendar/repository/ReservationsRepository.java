package com.calendar.webcalendar.repository;

import com.calendar.webcalendar.model.ReservationsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ReservationsRepository extends JpaRepository<ReservationsModel, Long> {


    //SELECT * FROM ReservationModel WHERE date = '?' AND start = '?' AND email = '?'
    @Query("SELECT r FROM ReservationsModel r WHERE r.date = :date AND r.start = :start")
    Optional<ReservationsModel> findReservationsModelByDateAndStart(LocalDate date, String start);

    @Query("SELECT r FROM ReservationsModel r WHERE r.id = :id AND r.email = :email")
    Optional<ReservationsModel> findReservationsModelByIdAndEmail(Long id, String email);


}
