package org.craftedsw.tripservicekata.trip;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TripServiceTest {

  @Test
  void should_throw_when_no_user_is_logged_in() {
    User user = new User();
    TripService tripService = new TripService() {
      User getLoggedUser() {
        return null;
      }
    };
    assertThatThrownBy(() -> tripService.getTripsByUser(user)).isInstanceOf(
        UserNotLoggedInException.class);
  }

  @Test
  void should_return_empty_list_when_loggedUser_has_no_friend() {
    User user = new User();
    TripService tripService = new TripService() {
      User getLoggedUser() {
        return new User();
      }
    };
    List<Trip> listTrip = tripService.getTripsByUser(user);

    assertThat(listTrip).isEmpty();
  }

}
