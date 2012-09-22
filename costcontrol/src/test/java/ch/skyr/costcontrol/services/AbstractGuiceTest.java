package ch.skyr.costcontrol.services;

import org.junit.After;
import org.junit.Before;

import ch.skyr.costcontrol.core.DataProvider;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.ServletModule;
public class AbstractGuiceTest {
    private Injector injector;

    @Before
    public void setupGuice() {
        final Module testModule = createModule();
        injector = Guice.createInjector(testModule);
        //
        injector.getInstance(PersistService.class).start();
    }

    protected Module createModule() {
        return new TestModule();
    }

    @After
    public void teardownGuice() {
        injector.getInstance(PersistService.class).stop();
    }

    protected Injector getInjector() {
        return injector;
    }

    protected class TestModule extends ServletModule {
        //    @Override
        //    protected void configure() {
        //        // Business object bindings go here.
        //        //
        //        //        // regular bind example
        //        //        bind(SearchStatisticQueries.class).to(DatastoreSearchStatisticQueries.class);
        //        //        
        //        //        // bind example with generics
        //        //        bind(new TypeLiteral<DatastoreRepository<Profile>>() {
        //        //        }).to(ProfileRepository.class);
        //        //
        //        //        //bind example to explicite instance 
        //        //        bind(UserService.class).toProvider(new Provider<UserService>() {
        //        //            @Override
        //        //            public UserService get() {
        //        //                return UserServiceFactory.getUserService();
        //        //            }
        //        //        });
        //        //
        //        bind(UserService.class).to(UserService.class);
        //        bind(DataProvider.class).to(DataProvider.class);
        //        //
        //    }
        @Override
        protected void configureServlets() {
            install(new JpaPersistModule(DataProvider.PERSISTENCE_UNIT_NAME));
            filter("/*").through(PersistFilter.class);
            //
            //            bind(UserService.class).to(UserService.class);
            //            bind(DataProvider.class).to(DataProvider.class);
        }
    }
}