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

  @Test
  void trips_should_be_empty_users_are_not_friend() {

    User user = new User();
    user.addFriend(new User());
    List<Trip> trips = new TripServiceMockWithUser().getTripsByUser(user);
    assertThat(trips).isEmpty();
  }

  @Test
  void trips_should_not_be_empty_users_are_friend() {

    User user = new User();
    TripServiceMockWithUser tripServiceMockWithUser = new TripServiceMockWithUser();
    User loggedUser = tripServiceMockWithUser.getLoggedUser();
    user.addFriend(loggedUser);
    List<Trip> trips = tripServiceMockWithUser.getTripsByUser(user);
    assertThat(trips).isNotEmpty();
  }

  static class TripServiceMockWithNullUser extends TripService {

    TripServiceMockWithNullUser() {
      super(new TripDAOImpl());
    }

    @Override
    public User getLoggedUser() {
      return null;
    }
  }

  static class TripServiceMockWithUser extends TripService {

    User loggedUser = new User();

    TripServiceMockWithUser() {
      super(new TripDAOMock());
    }

    @Override
    public User getLoggedUser() {
      return loggedUser;
    }
  }

  static class TripDAOMock implements TripDAO {

    @Override
    public List<Trip> tripsByUser(User user) {
      return List.of(new Trip());
    }
  }
}
