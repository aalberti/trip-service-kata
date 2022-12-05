package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

  public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
    List<Trip> tripList = new ArrayList<>();
    User loggedUser = getLoggedUser();
    if (loggedUser != null) {
      boolean isFriend = isFriend(user, loggedUser);
      if (isFriend) {
        tripList = findTripsByUser(user);
      }
      return tripList;
    } else {
      throw new UserNotLoggedInException();
    }
  }

  private boolean isFriend(User user, User loggedUser) {
    for (User friend : user.getFriends()) {
      if (friend.equals(loggedUser)) {
        return true;
      }
    }
    return false;
  }

  List<Trip> findTripsByUser(User user) {
    return TripDAO.findTripsByUser(user);
  }

  User getLoggedUser() {
    return UserSession.getInstance().getLoggedUser();
  }
}
