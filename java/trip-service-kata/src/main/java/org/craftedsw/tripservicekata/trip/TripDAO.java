package org.craftedsw.tripservicekata.trip;

import java.util.List;
import org.craftedsw.tripservicekata.user.User;

public interface TripDAO {

  List<Trip> tripsByUser(User user);
}
