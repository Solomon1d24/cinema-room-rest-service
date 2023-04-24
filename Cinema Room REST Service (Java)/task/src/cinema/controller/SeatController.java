package cinema.controller;

import cinema.dto.RefundTicketDTO;
import cinema.exception.NotAvailableSeatException;
import cinema.exception.WrongTokenException;
import cinema.model.Seat;
import cinema.model.Statistics;
import cinema.model.Summary;
import cinema.model.Ticket;
import cinema.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SeatController {

    private SeatService service;

    @Autowired
    public SeatController(SeatService service) {
        this.service = service;
    }

    @GetMapping("/seats")
    public Summary getSeats() {
        return service.getSeats();
    }

    @PostMapping("/purchase")
    public Ticket bookTicket(@RequestBody Seat requestedSeat) throws NotAvailableSeatException {
        return service.bookTicket(requestedSeat);
    }

    @PostMapping("/return")
    public RefundTicketDTO returnTicket(@RequestBody Ticket ticket) throws WrongTokenException {
        return service.returnTicket(ticket);
    }

    @PostMapping("/stats")
    public Statistics getStatistics(@RequestParam(required = false) String password){
        return service.getStatistics(password);
    }


}
