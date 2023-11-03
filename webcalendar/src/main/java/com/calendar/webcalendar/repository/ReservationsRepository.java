package com.calendar.webcalendar.repository;

import com.calendar.webcalendar.model.ReservationsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ReservationsRepository extends JpaRepository<ReservationsModel, Long> {


    @Query("SELECT r FROM ReservationsModel r WHERE r.date = :date AND r.start = :start")
    Optional<ReservationsModel> findReservationsModelByDateAndStart(@Param("date") LocalDate date, @Param("start") String start);

}
