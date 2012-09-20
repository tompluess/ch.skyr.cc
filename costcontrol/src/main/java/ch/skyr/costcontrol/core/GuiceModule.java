package ch.skyr.costcontrol.core;

import com.google.inject.AbstractModule;
/**
 * This Guice module sets up the bindings used in this Wicket application, including the JDO
 * PersistenceManager.
 */
public class GuiceModule extends AbstractModule {
    @Override
    protected void configure() {
        // Business object bindings go here.
        //
        //        // regular bind example
        //        bind(SearchStatisticQueries.class).to(DatastoreSearchStatisticQueries.class);
        //	    
        //	    // bind example with generics
        //		bind(new TypeLiteral<DatastoreRepository<Profile>>() {
        //		}).to(ProfileRepository.class);
        //
        //        //bind example to explicite instance 
        //        bind(UserService.class).toProvider(new Provider<UserService>() {
        //            @Override
        //            public UserService get() {
        //                return UserServiceFactory.getUserService();
        //            }
        //        });
        //
        bind(DataProvider.class).to(DataProvider.class);
        //
    }
}
