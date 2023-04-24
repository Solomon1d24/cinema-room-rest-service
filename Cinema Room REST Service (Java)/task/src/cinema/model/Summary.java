package cinema.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Summary {

    @JsonProperty("total_rows")
    private int rowCount;
    @JsonProperty("total_columns")
    private int columnCount;
    @JsonProperty("available_seats")
    private List<Seat> availableSeats;

    @JsonIgnore
    private Statistics statistics;

    @JsonIgnore
    private Map<UUID,Ticket> soldTickets;

    public Summary(int rowCount, int columnCount, List<Seat> availableSeats, Map<UUID, Ticket> soldTickets) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.availableSeats = availableSeats;
        this.soldTickets = soldTickets;
    }

    public Summary() {
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    public List<Seat> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(List<Seat> availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Map<UUID, Ticket> getSoldTickets() {
        return soldTickets;
    }

    public void setSoldTickets(Map<UUID, Ticket> soldTickets) {
        this.soldTickets = soldTickets;
    }

    public Statistics getStatistics() {
        int currentIncome = soldTickets.values().stream().mapToInt(t -> t.getSeat().getPrice()).sum();
        int numberOfAvailableSeats = availableSeats.size() - soldTickets.size();
        int numberOfPurchasedTickets = soldTickets.size();
        statistics = new Statistics(currentIncome,numberOfAvailableSeats,numberOfPurchasedTickets);
        return statistics;
    }
}
