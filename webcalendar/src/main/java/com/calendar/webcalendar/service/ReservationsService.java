package com.calendar.webcalendar.service;

import com.calendar.webcalendar.model.ReservationsModel;
import com.calendar.webcalendar.repository.ReservationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationsService {

    private final ReservationsRepository reservationsRepository;

    public ReservationsService(ReservationsRepository reservationsRepository) {
        this.reservationsRepository = reservationsRepository;
    }

    @Autowired
    public List<ReservationsModel> getReservations() {
        return reservationsRepository.findAll();
    }

    public ReservationsModel addNewReservation(ReservationsModel reservationsModel) {
        Optional<ReservationsModel> reservationsByDateAndStart = reservationsRepository
                .findReservationsModelByDateAndStart(
                        reservationsModel.getDate(),
                        reservationsModel.getStart()
                );

        if (reservationsModel.getPlayerOne() == null
                || reservationsModel.getPlayerOne().isEmpty()
                || reservationsModel.getPlayerTwo() == null
                || reservationsModel.getPlayerTwo().isEmpty()) {
            throw new IllegalStateException("Player not provided");
        }

        if (reservationsByDateAndStart.isPresent()) {
            throw new IllegalStateException("This reservation slot is already taken!");
        }

        reservationsRepository.save(reservationsModel);
        return reservationsModel;

    }

    public ReservationsModel deleteSlotReservation(ReservationsModel reservationsModel) {

        boolean exists = reservationsRepository.existsById(reservationsModel.getId());

        // Check if the id reservation exist
        if (!exists) {
            throw new IllegalStateException("reservation slot with id " + reservationsModel.getId() + " does not exists");
        }

        //Check if the email correspond to the one use during the booking
        Optional<ReservationsModel> reservationsByIdAndEmail = reservationsRepository
                .findReservationsModelByIdAndEmail(
                        reservationsModel.getId(),
                        reservationsModel.getEmail()
                );

        if (reservationsByIdAndEmail.isEmpty()) {
            throw new IllegalStateException(
                    "the reservation slot with this id: " + reservationsModel.getId() + " does not have this email"
            );
        }

        reservationsRepository.deleteById(reservationsModel.getId());

        return reservationsModel;
    }
}
