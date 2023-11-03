package com.calendar.webcalendar.config;

import com.calendar.webcalendar.model.AvailabilitiesModel;
import com.calendar.webcalendar.repository.AvailabilitiesRepository;
import com.calendar.webcalendar.tools.Tuple;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class AvailabiltiesConfig {
   /* public List<Tuple<LocalDate, String, String>> getLocalDateOfActualYear() {
        List<Tuple<LocalDate, String, String>> dates = new ArrayList<>();
        LocalDateTime dateNow = LocalDateTime.now();
        LocalDateTime dateEnd = LocalDateTime.of(LocalDateTime.now().getYear(), Month.DECEMBER, 31, 23,59,59);

        //Iterate over days of the actual year
        for (LocalDateTime currDate = dateNow; currDate.isBefore(dateEnd); currDate = currDate.plusDays(1)) {

            LocalDateTime nowAt9 = currDate.withHour(17).withMinute(0).withSecond(0).withNano(0);
            LocalDateTime nowAt12 = currDate.withHour(21).withMinute(0).withSecond(0).withNano(0);

            //Iterate one one slot from 09:00 to 12:00
            for (LocalDateTime currTime = nowAt9; currTime.isBefore(nowAt12);) {

                String hourMinStart = String.format("%02d",currTime.getHour()) + ":" + String.format("%02d", currTime.getMinute());

                currTime = currTime.plusMinutes(20);

                String hourMinEnd = String.format("%02d",currTime.getHour()) + ":" + String.format("%02d", currTime.getMinute());

                Tuple tuple = new Tuple<>(currDate.toLocalDate(), hourMinStart, hourMinEnd);
                dates.add(tuple);
            }

        }
        return dates;
    }

    @Bean
    CommandLineRunner commandLineRunnerAvailabilities(AvailabilitiesRepository availabilityRepository) {
        return args -> {
            //need to find a way to convert Sat Jul 30 2022 in front to 2022-07-30 the localDateFormat => then change attribut to String to LocalDate in model
            AvailabilitiesModel slot1 = new AvailabilitiesModel(
                    LocalDate.of(
                            LocalDate.now().getYear(),
                            LocalDate.now().getMonth().plus(1),
                            LocalDate.now().getDayOfMonth()
                    ),
                    "09:00",
                    "09:15"
            ); //"Sat Jul 30 2022" => "2022-07-30"

            AvailabilitiesModel slot2 = new AvailabilitiesModel(
                    LocalDate.of(
                            2022,
                            Month.JULY,
                            29
                    ),
                    "09:15",
                    "09:30"
            ); // "Fri Jul 29 2022" => 2022-07-29

            var creneaux = getLocalDateOfActualYear();

            List<AvailabilitiesModel> availabilitiesModelList = creneaux.stream().map(tuple-> new AvailabilitiesModel(tuple.getL(), tuple.getR(), tuple.getR2())).collect(Collectors.toList());

            availabilityRepository.saveAll(
                    availabilitiesModelList
            );

        };
    }*/
}
