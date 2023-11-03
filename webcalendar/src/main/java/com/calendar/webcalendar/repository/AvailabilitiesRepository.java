package com.calendar.webcalendar.repository;

import com.calendar.webcalendar.model.AvailabilitiesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@Repository
public interface AvailabilitiesRepository extends JpaRepository<AvailabilitiesModel, Long> {

    @Query("SELECT a FROM AvailabilitiesModel a WHERE a.date = :date AND a.start = :start")
    Optional<AvailabilitiesModel> findAvailabilitiesModelByDateAndStart(@Param("date")LocalDate date, @Param("start")String start);

    @Query("SELECT a FROM AvailabilitiesModel a WHERE a.date = :date")
    Collection<Optional<AvailabilitiesModel>> findAvailabilitiesModelByDate(@Param("date")LocalDate date);

}
