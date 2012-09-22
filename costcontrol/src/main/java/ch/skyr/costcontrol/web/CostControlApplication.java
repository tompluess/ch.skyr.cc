package ch.skyr.costcontrol.web;

import java.util.Locale;

import org.apache.wicket.Session;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

import ch.skyr.costcontrol.core.GuiceModule;

import com.google.inject.Module;
/**
 * Application object for your web application.
 */
public class CostControlApplication extends WebApplication {
    @Override
    public Class<Home> getHomePage() {
        return Home.class;
    }

    @Override
    protected void init() {
        super.init();
        // Guice Config
        final GuiceComponentInjector injector = new GuiceComponentInjector(this, createModule());
        getComponentInstantiationListeners().add(injector);
    }

    protected Module createModule() {
        return new GuiceModule();
    }

    @Override
    public Session newSession(final Request request, final Response response) {
        final Session newSession = super.newSession(request, response);
        newSession.setLocale(Locale.ENGLISH);
        return newSession;
    }
}
