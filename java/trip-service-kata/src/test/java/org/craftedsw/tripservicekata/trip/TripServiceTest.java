package org.craftedsw.tripservicekata.trip;

import org.assertj.core.api.Assertions;
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;

public class TripServiceTest {

  @Test
  void should_throw_exception_if_not_logged_user() {

    Assertions.assertThatThrownBy(() -> new TripServiceMock().getTripsByUser(null))
        .isInstanceOf(UserNotLoggedInException.class);
  }

  class TripServiceMock extends TripService {

    @Override
    public User getLoggedUser() {
      return null;
    }
  }
}
