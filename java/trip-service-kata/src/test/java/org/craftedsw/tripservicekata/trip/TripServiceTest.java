package org.craftedsw.tripservicekata.trip;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;

public class TripServiceTest {

  @Test
  void should_throw_exception_if_not_logged_user() {

    assertThatThrownBy(() -> new TripServiceMockWithNullUser().getTripsByUser(null))
        .isInstanceOf(UserNotLoggedInException.class);
  }

  @Test
  void should_log_user() {
    List<Trip> trips = new TripServiceMockWithUser().getTripsByUser(new User());
    assertThat(trips).isEmpty();
  }

  static class TripServiceMockWithNullUser extends TripService {

    @Override
    public User getLoggedUser() {
      return null;
    }
  }

  static class TripServiceMockWithUser extends TripService {

    @Override
    public User getLoggedUser() {
      return new User();
    }
  }
}
