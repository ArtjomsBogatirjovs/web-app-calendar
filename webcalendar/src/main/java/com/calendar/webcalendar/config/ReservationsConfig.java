package com.calendar.webcalendar.config;

import com.calendar.webcalendar.model.ReservationsModel;
import com.calendar.webcalendar.repository.AvailabilitiesRepository;
import com.calendar.webcalendar.repository.ReservationsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class ReservationsConfig {

 /*   @Bean
    CommandLineRunner commandLineRunnerReservation(ReservationsRepository reservationsRepository) {
        return args -> {
            ReservationsModel slot1 = new ReservationsModel(
                    LocalDate.of(
                            LocalDate.now().getYear(),
                            LocalDate.now().getMonth(),
                            LocalDate.now().getDayOfMonth()
                    ),
                    "11:00",
                    "11:15",
                    "Meeting",
                    "john.doe@gmail.com"
            );

            ReservationsModel slot2 = new ReservationsModel(
                    LocalDate.of(
                            LocalDate.now().getYear(),
                            LocalDate.now().getMonth(),
                            LocalDate.now().getDayOfMonth() + 1
                    ),
                    "11:30",
                    "11:45",
                    "Meeting",
                    "jane.snow@gmail.com"
            );

            reservationsRepository.saveAll(
                    List.of(slot1, slot2)
            );
        };
    }*/
}
