package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.DefaultUserSession;
import org.craftedsw.tripservicekata.user.User;

import java.util.ArrayList;
import java.util.List;

public class TripService {

  private final TripDAO tripDAO;
  private final DefaultUserSession userSession;

  public TripService(TripDAO tripDAO, DefaultUserSession userSession) {
    this.tripDAO = tripDAO;
	  this.userSession = userSession;
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
	  return userSession.currentLoggedUser();
  }

}
