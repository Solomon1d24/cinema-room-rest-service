package cinema.dto;

import cinema.model.Seat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RefundTicketDTO {

    @JsonProperty("returned_ticket")
    private Seat seat;

    public RefundTicketDTO(Seat seat) {
        this.seat = seat;
    }

    public RefundTicketDTO() {}

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}
