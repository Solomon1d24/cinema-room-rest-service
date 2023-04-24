package cinema.service;

import cinema.dto.RefundTicketDTO;
import cinema.exception.NotAvailableSeatException;
import cinema.exception.WrongTokenException;
import cinema.model.Seat;
import cinema.model.Summary;
import cinema.model.Ticket;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class SeatService {

    private Summary summary;

    private ModelMapper modelMapper;

    @Autowired
    public SeatService(Summary summary, ModelMapper modelMapper) {
        this.summary = summary;
        this.modelMapper = modelMapper;
    }

    public Summary getSeats() {
        return summary;
    }

    public Ticket bookTicket(Seat requestedSeat) throws NotAvailableSeatException {
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

        Ticket soldTicket = new Ticket(UUID.randomUUID(), seat);

        summary.getSoldTickets().putIfAbsent(soldTicket.getToken(), soldTicket);

        return soldTicket;
    }

    public RefundTicketDTO returnTicket(Ticket ticket) throws WrongTokenException {
        Map<UUID, Ticket> soldTickets = summary.getSoldTickets();
        if (!soldTickets.containsKey(ticket.getToken())) {
            throw new WrongTokenException("Wrong token!");
        }
        Ticket soldTicket = soldTickets.get(ticket.getToken());
        soldTicket.getSeat().setBooked(false);
        soldTickets.remove(ticket.getToken(), soldTicket);

        return modelMapper.map(soldTicket, RefundTicketDTO.class);
    }
}
