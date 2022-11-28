package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TripServiceTest {
    @Test
    void logged_user_without_friend() {
        TripService tripService = new TripServiceDouble(new User());
        List<Trip> trips = tripService.getTripsByUser(new User());
        assertThat(trips).isEqualTo(new ArrayList<>());
    }

    private static class TripServiceDouble extends TripService {
        private final User user;

        public TripServiceDouble(User user) {
            this.user = user;
        }

        @Override
        User getLoggedUser() {
            return user;
        }
    }
}
