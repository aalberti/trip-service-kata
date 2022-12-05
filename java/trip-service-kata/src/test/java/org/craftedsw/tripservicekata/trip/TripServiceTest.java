package org.craftedsw.tripservicekata.trip;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;

public class TripServiceTest {

  @Test
  void logged_user_without_friend() {
    TripService tripService = new TripServiceDouble(new User(), null);
    List<Trip> trips = tripService.getTripsByUser(new User());
    assertThat(trips).isEqualTo(new ArrayList<>());
  }

  @Test
  void logged_user_with_friend() {
    User user = new User();
    User friend = new User();
    user.addFriend(friend);
    TripService tripService = new TripServiceDouble(friend, List.of(new Trip()));
    assertThat(tripService.getTripsByUser(user)).hasSize(1);
  }

  @Test
  void un_logged_user_with_friend() {
    TripService tripService = new TripServiceDouble(null, List.of(new Trip()));
    assertThatThrownBy(() -> tripService.getTripsByUser(null)).isInstanceOf(
        UserNotLoggedInException.class);
  }

  private static class TripServiceDouble extends TripService {

    private final User user;
    private List<Trip> trips;

    public TripServiceDouble(User user, List<Trip> trips) {
      this.user = user;
      this.trips = trips;
    }

    @Override
    List<Trip> findTripsByUser(User user) {

      return trips;
    }

    @Override
    User getLoggedUser() {
      return user;
    }
  }
}
