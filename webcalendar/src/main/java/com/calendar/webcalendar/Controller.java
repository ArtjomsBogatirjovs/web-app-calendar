package com.calendar.webcalendar;

import com.calendar.webcalendar.model.AvailabilitiesModel;
import com.calendar.webcalendar.model.ReservationsModel;
import com.calendar.webcalendar.service.AvailabilitiesService;
import com.calendar.webcalendar.service.ReservationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin()
@RestController
@RequestMapping(path = "calendar")
public class Controller {

    private final AvailabilitiesService availabilityService;

    private final ReservationsService reservationsService;

    @Autowired
    public Controller(AvailabilitiesService availabilityService, ReservationsService reservationsService) {
        this.availabilityService = availabilityService;
        this.reservationsService = reservationsService;
    }

    // ########################################AVAILABILITIES############################################

    //List the availabilities
    @GetMapping("/availabilities/get")
    public List<AvailabilitiesModel> getAllAvailabilities() {
        return availabilityService.getAllAvailabilities();
    }

    @GetMapping("availabilities/get/day/{slotDate}") //slotDate = date of the day select in the front
    public Collection<Optional<AvailabilitiesModel>> getAvailabilitiesOfDay(@PathVariable("slotDate") String date) {
        return availabilityService.getAvailabilityOfDay(date);
    }

    @PostMapping("/availabilities/post")
    public AvailabilitiesModel createAvailability(@RequestBody AvailabilitiesModel availabilitiesModel) {
        return availabilityService.addNewAvailability(availabilitiesModel);
    }

    @DeleteMapping(path = "/availabilities/delete/{slotId}")
    public Long deleteAvailability(@PathVariable("slotId") Long availabilityId) {
        return availabilityService.deleteSlotAvailability(availabilityId);
    }

    // ########################################RESERVATIONS#################################

    //List the reservations
    @GetMapping("/reservations/get")
    public List<ReservationsModel> getReservation() {
        var reservations = reservationsService.getReservations();
        return reservations.stream()
                .sorted(Comparator.comparing(ReservationsModel::getDate))
                .collect(Collectors.toList());
    }

    //create a reservation and later delete from availability
    @PostMapping("/reservations/post")
    public ResponseEntity<?> createReservation(@RequestBody ReservationsModel reservationsModel) {
        try {
            return ResponseEntity.ok(reservationsService.addNewReservation(reservationsModel));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }

    }

    @DeleteMapping(path = "/reservations/delete")
    public ReservationsModel deleteReservation(@RequestBody ReservationsModel reservationsModel) {
        return reservationsService.deleteSlotReservation(reservationsModel);
    }

}
