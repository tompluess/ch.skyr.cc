package ch.skyr.costcontrol.core;

import ch.skyr.costcontrol.queries.ProfileQueries;
import ch.skyr.costcontrol.services.HqlProfileQueries;

import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.ServletModule;
/**
 * This Guice module sets up the bindings used in this Wicket application, including the JDO
 * PersistenceManager.
 */
public class GuiceModule extends ServletModule {
    //    @Override
    //    protected void configure() {
    //        // Business object bindings go here.
    //        //
    //        //        // regular bind example
    //        //        bind(SearchStatisticQueries.class).to(DatastoreSearchStatisticQueries.class);
    //        //	    
    //        //	    // bind example with generics
    //        //		bind(new TypeLiteral<DatastoreRepository<Profile>>() {
    //        //		}).to(ProfileRepository.class);
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
        bind(ProfileQueries.class).to(HqlProfileQueries.class);
    }
}
