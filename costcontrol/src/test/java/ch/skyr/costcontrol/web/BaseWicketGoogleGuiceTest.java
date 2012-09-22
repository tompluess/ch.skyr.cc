package ch.skyr.costcontrol.web;

import static org.mockito.Mockito.*;

import javax.servlet.Filter;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.After;
import org.junit.Before;

import ch.skyr.costcontrol.entities.user.Profile;
import ch.skyr.costcontrol.entities.user.User;
import ch.skyr.costcontrol.queries.ProfileQueries;
import ch.skyr.costcontrol.services.AbstractGuiceTest;
import ch.skyr.costcontrol.services.UserService;

import com.google.common.collect.Lists;
import com.google.inject.Module;
import com.google.inject.servlet.GuiceFilter;
/**
 * This base test class sets up a simulated Google App Engine + Wicket environment. Wicket pages can
 * be tested using the protected 'tester' field, and development-mode GAE services are made
 * available to tests.
 */
public abstract class BaseWicketGoogleGuiceTest extends AbstractGuiceTest {
    protected WicketTester tester;

    @Before
    public void setUpFilteringWicketTesterOnce() {
        tester = new FilteringWicketTester(createWebApplication(), createServletFilters());
    }

    @After
    public void tearDownFilteringWicketTesterOnce() {
        tester.destroy();
    }

    /**
     * The subclass should override this method and return new, uninitialized Filter objects, in the
     * order in which they should be executed.
     * 
     * @return the servlet filters; the default implementation returns an empty array
     */
    protected WebApplication createWebApplication() {
        return new CostControlApplication() {
            @Override
            protected Module createModule() {
                return BaseWicketGoogleGuiceTest.this.createModule();
            }
        };
    }

    /**
     * The subclass must return a new WebApplication subclass representing the Wicket application
     * being tested.
     * 
     * @return the WebApplication instance
     */
    protected Filter[] createServletFilters() {
        return new Filter[] { new GuiceFilter()
        // , new PersistenceManagerFilter()
        };
    }

    @Override
    protected Module createModule() {
        return new AbstractGuiceTest.TestModule() {
            protected ProfileQueries profileQueriesMock = mock(ProfileQueries.class);
            protected UserService us = mock(UserService.class);

            @Override
            protected void configureServlets() {
                super.configureServlets();
                final String userid = "12341234";
                final Profile testProfile = new Profile(userid);
                when(us.getCurrentUser()).thenReturn(new User("tester@skyr.ch", userid));
                when(profileQueriesMock.getProfile(userid)).thenReturn(testProfile);
                when(profileQueriesMock.getProfiles()).thenReturn(Lists.newArrayList(testProfile));
                bind(UserService.class).toInstance(us);
                bind(ProfileQueries.class).toInstance(profileQueriesMock);
            }
        };
    }
}