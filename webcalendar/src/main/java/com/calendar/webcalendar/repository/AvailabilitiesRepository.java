package com.calendar.webcalendar.repository;

import com.calendar.webcalendar.model.AvailabilitiesModel;
import com.calendar.webcalendar.model.ReservationsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@Repository
public interface AvailabilitiesRepository extends JpaRepository<AvailabilitiesModel, Long> {

    //SELECT * FROM AvailbilitiesModel WHERE date = '?' AND start = '?'
    //the @Query() does the above request but spring can auto do it with the name of the method here : findAvailabilitiesModelByDateAndStart

    @Query("SELECT a FROM AvailabilitiesModel a WHERE a.date = :date AND a.start = :start")
    Optional<AvailabilitiesModel> findAvailabilitiesModelByDateAndStart(LocalDate date, String start);

    @Query("SELECT a FROM AvailabilitiesModel a WHERE a.date = :date")
    Collection<Optional<AvailabilitiesModel>> findAvailabilitiesModelByDate(LocalDate date);

}
