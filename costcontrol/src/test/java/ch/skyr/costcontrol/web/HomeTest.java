package ch.skyr.costcontrol.web;

import static org.mockito.Mockito.*;

import org.junit.Test;

import ch.skyr.costcontrol.queries.ProfileQueries;
import ch.skyr.costcontrol.services.AbstractGuiceTest;
import ch.skyr.costcontrol.services.UserService;

import com.google.inject.Module;
public class HomeTest extends BaseWicketGoogleGuiceTest {
    @Test
    public void rendering_initially_successful() {
        tester.startPage(Home.class);
        tester.assertRenderedPage(Home.class);
    }

    @Override
    protected Module createModule() {
        return new AbstractGuiceTest.TestModule() {
            protected ProfileQueries profileQueriesMock = mock(ProfileQueries.class);
            protected UserService us = mock(UserService.class);

            @Override
            protected void configureServlets() {
                super.configureServlets();
                bind(UserService.class).toInstance(us);
                bind(ProfileQueries.class).toInstance(profileQueriesMock);
            }
        };
    }
}
