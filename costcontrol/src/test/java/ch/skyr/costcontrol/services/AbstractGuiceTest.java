package ch.skyr.costcontrol.services;

import org.junit.After;
import org.junit.Before;

import ch.skyr.costcontrol.core.DataProvider;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;
public class AbstractGuiceTest {
    private Injector injector;
    protected Module testModule;

    @Before
    public void setupGuice() {
        testModule = new Module() {
            @Override
            public void configure(final Binder binder) {
                // ... binder.bind(type);
            }
        };
        final Module persistenceModule = new JpaPersistModule(DataProvider.PERSISTENCE_UNIT_NAME);
        injector = Guice.createInjector(persistenceModule, testModule);
        //
        injector.getInstance(PersistService.class).start();
    }

    @After
    public void teardownGuice() {
        injector.getInstance(PersistService.class).stop();
    }

    protected Injector getInjector() {
        return injector;
    }
}