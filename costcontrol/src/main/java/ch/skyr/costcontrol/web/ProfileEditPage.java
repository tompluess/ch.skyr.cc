package ch.skyr.costcontrol.web;

import javax.persistence.EntityManager;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

import ch.skyr.costcontrol.entities.user.Profile;

import com.google.inject.Inject;
public class ProfileEditPage extends AbstractAuthPage {
    static final String FORM_EDIT_PROFILE = "formEditProfile";
    private static final long serialVersionUID = 1L;
    @Inject
    private EntityManager em;

    public ProfileEditPage() {
        super();
    }

    @Override
    protected void checkForExistingProfile() {
        // do not check
    }

    @Override
    protected void initComponents() {
        Profile profile = getCurrentUserProfile();
        if (profile == null) {
            profile = new Profile(getCurrentUser().getUserId());
        }
        add(new InputForm(FORM_EDIT_PROFILE, profile));
        add(new Label("labelNickname", getCurrentUser().getNickname()));
    }

    private class InputForm extends Form<Profile> {
        private static final long serialVersionUID = 1L;
        private final Profile profile;

        public InputForm(final String name, final Profile profile) {
            super(name, new CompoundPropertyModel<Profile>(profile));
            this.profile = profile;
            add(new FeedbackPanel("feedback"));
            add(new TextField<String>("firstname").setRequired(true));
            add(new TextField<String>("name").setRequired(true));
            add(new TextField<String>("profession").setRequired(true));
            add(new TextField<String>("address").setRequired(true));
            add(new TextArea<String>("description").setRequired(true));
        }

        @Override
        protected void onSubmit() {
            em.persist(this.getProfile());
            setResponsePage(ProfilePage.class);
        }

        public Profile getProfile() {
            return profile;
        }
    }
}
