package org.craftedsw.tripservicekata.trip;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;

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

}
