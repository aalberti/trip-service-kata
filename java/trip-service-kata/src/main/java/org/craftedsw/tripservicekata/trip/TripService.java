package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {

		User loggedUser = getLoggedUser();
		if (loggedUser != null) {
			return getTripsForLoggedUser(user, loggedUser);
		} else {
			throw new UserNotLoggedInException();
		}
	}

	private List<Trip> getTripsForLoggedUser(User user, User loggedUser) {
		boolean isFriend = false;
		for (User friend : user.getFriends()) {
			if (friend.equals(loggedUser)) {
				isFriend = true;
				break;
			}
		}
		List<Trip> tripList = new ArrayList<>();
		if (isFriend) {
			tripList = findTripsByUser(user);
		}
		return tripList;
	}

	List<Trip> findTripsByUser(User user) {
		return TripDAO.findTripsByUser(user);
	}

	User getLoggedUser() {
		return UserSession.getInstance().getLoggedUser();
	}

}
