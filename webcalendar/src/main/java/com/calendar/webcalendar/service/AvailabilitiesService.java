package com.calendar.webcalendar.service;

import com.calendar.webcalendar.model.AvailabilitiesModel;
import com.calendar.webcalendar.repository.AvailabilitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class AvailabilitiesService {

    private final AvailabilitiesRepository availabilityRepository;

    public AvailabilitiesService(AvailabilitiesRepository availabilityRepository) {
        this.availabilityRepository = availabilityRepository;
    }

    @Autowired
    public List<AvailabilitiesModel> getAllAvailabilities() {
        return availabilityRepository.findAll();
    }

    public AvailabilitiesModel addNewAvailability(AvailabilitiesModel availabilitiesModel) {
        Optional<AvailabilitiesModel> availableByDateAndStart = availabilityRepository
                .findAvailabilitiesModelByDateAndStart(
                        availabilitiesModel.getDate(),
                        availabilitiesModel.getStart()
                );

        if (availableByDateAndStart.isPresent()) {
            throw new IllegalStateException("this availability slot (Date and Start) is already present");
        }

        //save new availability in the calendar database
        availabilityRepository.save(availabilitiesModel);
        return availabilitiesModel;
    }

    public Long deleteSlotAvailability(Long AvailabilityId) {
        boolean exists = availabilityRepository.existsById(AvailabilityId);
        if (!exists) {
            throw new IllegalStateException("availabilty slot with id " + AvailabilityId + " does not exists");
        }
        availabilityRepository.deleteById(AvailabilityId);

        return AvailabilityId;
    }

    public Collection<Optional<AvailabilitiesModel>> getAvailabilityOfDay(String date) {

        // convert string with format 2016-08-16 to localDate with the same format
        LocalDate localDate = LocalDate.parse(date);

        Collection<Optional<AvailabilitiesModel>> availabilitiesOfDay = availabilityRepository
                .findAvailabilitiesModelByDate(localDate);

        if (availabilitiesOfDay.isEmpty()) {
            throw new IllegalStateException("there is no availabilities for this day");
        }



        return availabilitiesOfDay;
    }
}
