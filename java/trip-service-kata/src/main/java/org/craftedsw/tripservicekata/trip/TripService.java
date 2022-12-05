package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

  private TripDAO tripDAO;

  public TripService(TripDAO tripDAO) {
    this.tripDAO = tripDAO;
  }

  public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {

    User loggedUser = getLoggedUser();
    if (loggedUser != null) {
      return getTrips(user, loggedUser);
    } else {
      throw new UserNotLoggedInException();
    }
  }

  private List<Trip> getTrips(User user, User loggedUser) {
    boolean hasLoggedUserAsFriend = user.getFriends().stream().anyMatch(f -> f.equals(loggedUser));
    if (hasLoggedUserAsFriend) {
      return findTripsByUser(user);
    } else {
      return new ArrayList<>();
    }
  }

  List<Trip> findTripsByUser(User user) {
    return tripDAO.getTripsByUser(user);
  }

  User getLoggedUser() {
    return UserSession.getInstance().getLoggedUser();
  }
}
