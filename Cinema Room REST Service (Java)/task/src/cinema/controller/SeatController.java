package cinema.controller;

import cinema.exception.NotAvailableSeatException;
import cinema.model.Seat;
import cinema.model.Summary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class SeatController {

    private Summary summary;

    @Autowired
    public SeatController(Summary summary) {
        this.summary = summary;
    }

    @GetMapping("/seats")
    public Summary getSeats() {
        return summary;
    }

    @PostMapping("/purchase")
    public Seat bookTicket(@RequestBody Seat requestedSeat) {
        List<Seat> seats = summary.getAvailableSeats();
        Optional<Seat> seatOptional = seats.stream()
                .filter(s -> s.getRow() == requestedSeat.getRow())
                .filter(s -> s.getColumn() == requestedSeat.getColumn())
                .findAny();
        Seat seat = seatOptional.orElseThrow(
                () -> new NotAvailableSeatException("The number of a row or a column is out of bounds!"));
        if (seat.isBooked()) {
            throw new NotAvailableSeatException("The ticket has been already purchased!");
        }
        seat.setBooked(true);
        return seat;
    }
}
