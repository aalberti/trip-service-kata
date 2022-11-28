package org.craftedsw.tripservicekata.trip;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;

public class TripServiceTest {
  @Test
  void logged_user_without_friend() {
    TripService tripService = new TripServiceDouble(new User());
    List<Trip> trips = tripService.getTripsByUser(new User());
    assertThat(trips).isEqualTo(new ArrayList<>());
  }

  @Test
  void logged_user_with_friend() {
    User user = new User();
    User friend = new User();
    user.addFriend(friend);
    TripService tripService = new TripServiceDouble(friend);
    assertThat(tripService.getTripsByUser(user)).hasSize(1);
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
