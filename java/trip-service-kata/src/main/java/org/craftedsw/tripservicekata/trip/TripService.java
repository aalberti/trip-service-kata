package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

  private final TripDAO tripDAO;

  public TripService(TripDAO tripDAO) {
    this.tripDAO = tripDAO;
  }

  public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {

    User loggedUser = getLoggedUser();

    if (loggedUser == null) {
      throw new UserNotLoggedInException();
    }

    boolean isFriend = isFriend(user, loggedUser);
    return isFriend ? tripDAO.tripsByUser(user) : new ArrayList<>();
  }

  private boolean isFriend(User user, User loggedUser) {
    for (User friend : user.getFriends()) {
      if (friend.equals(loggedUser)) {
        return true;
      }
    }
    return false;
  }

  User getLoggedUser() {
    return UserSession.getInstance().getLoggedUser();
  }
}
