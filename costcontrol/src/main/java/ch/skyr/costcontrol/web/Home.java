package ch.skyr.costcontrol.web;

import org.apache.wicket.RestartResponseException;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.ExternalLink;

import ch.skyr.costcontrol.entities.user.User;
import ch.skyr.costcontrol.services.UserService;

import com.google.inject.Inject;
public class Home extends WebPage {
    private static final long serialVersionUID = 1L;
    @Inject
    private UserService userService;

    public Home() {
        final User user = userService.getCurrentUser();
        if (user != null) {
            throw new RestartResponseException(ProfilePage.class);
        }
        final ExternalLink signIn = new ExternalLink("sign-in", userService.createLoginURL("/" + getRequest().getClientUrl()));
        add(signIn);
    }
}
