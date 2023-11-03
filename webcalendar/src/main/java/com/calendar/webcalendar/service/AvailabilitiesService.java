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

        //si elle est pas presente dans reservation alors je peux la cree
        //si elle est dans reservation ou availabilties alors je la cree pas
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

    //	public List<String> hello() {
//		//need to find a way to convert Sat Jul 30 2022 in front to 2022-07-30 the localDateFormat => then change attribut to String to LocalDate in model
//		return List.of("Hello", "World!");
//	}

}
