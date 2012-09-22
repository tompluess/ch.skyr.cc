package ch.skyr.costcontrol.web;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

import ch.skyr.costcontrol.entities.user.Profile;
public class ProfilePage extends AbstractAuthPage {
    private static final long serialVersionUID = 1L;

    public ProfilePage() {
        super();
    }

    public ProfilePage(final PageParameters pageParams) {
        super(pageParams);
    }

    @Override
    protected void initComponents() {
        final Profile profile = determineProfile();
        add(new Label("profile.labelName", profile.getLabelName()));
        add(new Label("profile.profession", profile.getProfession()));
        add(new Label("profile.address", profile.getAddress()));
        add(new Label("profile.description", profile.getDescription()));
    }

    protected Profile determineProfile() {
        final StringValue userIdParam = getPageParameters().get("userId");
        if (userIdParam != null && !userIdParam.isEmpty()) {
            return profileQueries.getProfile(userIdParam.toString());
        } else {
            return getCurrentUserProfile();
        }
    }
}
