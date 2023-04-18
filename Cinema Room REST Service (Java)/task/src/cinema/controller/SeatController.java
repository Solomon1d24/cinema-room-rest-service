package cinema.controller;

import cinema.model.Seat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SeatController {


    private static class Summary {

        @JsonProperty("total_rows")
        private int rowCount;
        @JsonProperty("total_columns")
        private int columnCount;
        @JsonProperty("available_seats")
        private List<Seat> availableSeats;

        {
            rowCount = 9;
            columnCount = 9;
            availableSeats = new ArrayList<>();
            for (int i = 1; i <= rowCount; i++) {
                for (int k = 1; k <= columnCount; k++) {
                    Seat seat = new Seat(i, k);
                    availableSeats.add(seat);
                }
            }
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

    @GetMapping("/seats")
    public Summary getSeats() {
        return new Summary();
    }

    @GetMapping("/one")
    public int getOne() {
        return 1;
    }
}
