package ch.skyr.costcontrol.web;

import org.junit.Test;
public class ProfileEditPageTest extends BaseWicketGoogleGuiceTest {
    @Test
    public void rendering_initially_successful() {
        tester.startPage(ProfileEditPage.class);
        tester.assertRenderedPage(ProfileEditPage.class);
    }
}
