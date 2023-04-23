package cinema.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Summary {

    @JsonProperty("total_rows")
    private int rowCount;
    @JsonProperty("total_columns")
    private int columnCount;
    @JsonProperty("available_seats")
    private List<Seat> availableSeats;

    public Summary(int rowCount, int columnCount, List<Seat> availableSeats) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.availableSeats = availableSeats;
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
}
