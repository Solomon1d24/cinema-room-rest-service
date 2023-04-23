package cinema.Config;

import cinema.model.Seat;
import cinema.model.Summary;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SeatConfig {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Summary getSummary() {
        int rowCount = 9;
        int columnCount = 9;
        List<Seat> availableSeats = new ArrayList<>();
        for (int i = 1; i <= rowCount; i++) {
            for (int k = 1; k <= columnCount; k++) {
                Seat seat = new Seat(i, k);
                availableSeats.add(seat);
            }
        }
        return new Summary(rowCount, columnCount, availableSeats);
    }
}
