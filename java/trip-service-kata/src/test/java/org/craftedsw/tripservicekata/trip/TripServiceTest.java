package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TripServiceTest {

  @Test
  void logged_user_without_friend() {
    TripService tripService = new TripServiceDouble(new User(), null);
    List<Trip> trips = tripService.getTripsByUser(new User());
    assertThat(trips).isEmpty();
  }

  @Test
  void logged_lonely_user() {
    User user = new User();
    User friend = new User();
    User stranger = new User();
    user.addFriend(friend);
    TripService tripService = new TripServiceDouble(stranger, List.of(new Trip()));
    assertThat(tripService.getTripsByUser(user)).hasSize(0);
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

    public TripServiceDouble(User user, List<Trip> trips) {
      super(new TripServiceTest.TripDAODouble(trips), () -> user);
    }

  }

  private static class TripDAODouble extends TripDAO {
    private final List<Trip> trips;

    public TripDAODouble(List<Trip> trips) {
      this.trips = trips;
    }

    @Override
    public List<Trip> getTripsByUser(User user) {
      return trips;
    }
  }
}
