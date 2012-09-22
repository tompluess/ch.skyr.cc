package ch.skyr.costcontrol.web;

import org.apache.wicket.RestartResponseException;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import ch.skyr.costcontrol.entities.user.Profile;
import ch.skyr.costcontrol.entities.user.User;
import ch.skyr.costcontrol.queries.ProfileQueries;
import ch.skyr.costcontrol.services.UserService;

import com.google.inject.Inject;
public abstract class AbstractAuthPage extends WebPage {
    private static final long serialVersionUID = 1L;
    @Inject
    private UserService userService;
    @Inject
    protected ProfileQueries profileQueries;
    private Profile currentUserProfile;
    private User user;

    public AbstractAuthPage() {
        initAuthPage();
    }

    public AbstractAuthPage(final PageParameters pageParams) {
        super(pageParams);
        initAuthPage();
    }

    private void initAuthPage() {
        checkForAuthentication();
        checkForExistingProfile();
        initBaseComponents();
        initComponents();
    }

    private void checkForAuthentication() {
        if (getCurrentUser() == null) {
            throw new RestartResponseException(Home.class);
        }
    }

    private void initBaseComponents() {
        final Profile profile = getCurrentUserProfile();
        setNavigation();
        final Label userName = new Label("username", getCurrentUser().getNickname());
        final BookmarkablePageLink<ProfileEditPage> link = new BookmarkablePageLink<ProfileEditPage>("linkProfileEditPage", ProfileEditPage.class);
        //final BookmarkablePageLink<SkillsPage> linkSkillEdit = new BookmarkablePageLink<SkillsPage>("navSkills", SkillsPage.class);
        final ExternalLink signOut = new ExternalLink("sign-out", getUserService().createLogoutURL("/" + getRequest().getClientUrl()));
        add(new BookmarkablePageLink<ProfilePage>("navProfile", ProfilePage.class));
        //add(new BookmarkablePageLink<SearchPage>("navSearch", SearchPage.class));
        //        add(signOut);
        link.add(userName);
        add(link);
    }

    private void setNavigation() {
        final WebMarkupContainer chevronSearch = new WebMarkupContainer("auth-nav-search");
        final WebMarkupContainer chevronSkills = new WebMarkupContainer("auth-nav-skills");
        final WebMarkupContainer chevronProfile = new WebMarkupContainer("auth-nav-profile");
        add(chevronSearch);
        add(chevronSkills);
        add(chevronProfile);
    }

    /**
     * Implement the component binding here.
     */
    protected abstract void initComponents();

    protected void checkForExistingProfile() {
        if (getCurrentUserProfile() == null) {
            throw new RestartResponseException(ProfileEditPage.class);
        }
    }

    protected synchronized User getCurrentUser() {
        if (this.user == null) {
            this.user = userService.getCurrentUser();
        }
        return this.user;
    }

    protected String getUserId() {
        return getCurrentUser().getUserId();
    }

    protected synchronized Profile getCurrentUserProfile() {
        if (this.currentUserProfile == null) {
            this.currentUserProfile = profileQueries.getProfile(getCurrentUser().getUserId());
        }
        return this.currentUserProfile;
    }

    protected UserService getUserService() {
        return userService;
    }
}
