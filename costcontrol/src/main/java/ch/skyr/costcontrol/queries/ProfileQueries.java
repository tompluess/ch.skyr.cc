package ch.skyr.costcontrol.queries;

import java.util.List;

import ch.skyr.costcontrol.entities.user.Profile;
public interface ProfileQueries {
    Profile getProfile(String userId);

    List<Profile> getProfiles();

    List<Profile> searchByName(String searchUser);
}
