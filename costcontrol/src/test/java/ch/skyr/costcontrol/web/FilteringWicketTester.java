package ch.skyr.costcontrol.web;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.WicketTester;
/**
 * This is a WicketTester that first invokes a chain of servlet filters, exactly the way they would
 * be invoked within an application server, before processing the page request. This more closely
 * simulates the request process and is necessary to get full support of dependency injection
 * frameworks (such as Guice Servlet Extensions) that require a filter.
 */
public class FilteringWicketTester extends WicketTester {
    private List<Filter> servletFilters;

    public FilteringWicketTester(final WebApplication application, final Filter... servletFilters) {
        super(application);
        this.servletFilters = Arrays.asList(servletFilters);
        initFilters();
    }

    @Override
    public void submitForm(final String path) {
        try {
            // invoke the filter chain and then...
            doFilter(servletFilters.iterator(), new Runnable() {
                @Override
                public void run() {
                    // ...execute the form submission
                    FilteringWicketTester.super.submitForm(path);
                }
            });
        } catch (final IOException e) {
            throw new RuntimeException(e);
        } catch (final ServletException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {
        for (final Filter filter : servletFilters) {
            filter.destroy();
        }
        // This insures that this instance cannot be reused.
        servletFilters = null;
        super.destroy();
    }

    private void initFilters() {
        try {
            for (final Filter filter : servletFilters) {
                filter.init(new FilterConfig() {
                    @Override
                    public String getFilterName() {
                        return "Filter of type " + filter.getClass().getName();
                    }

                    @Override
                    public ServletContext getServletContext() {
                        return getApplication().getServletContext();
                    }

                    @Override
                    public String getInitParameter(final String name) {
                        return null;
                    }

                    @Override
                    public Enumeration<?> getInitParameterNames() {
                        return null;
                    }
                });
            }
        } catch (final ServletException e) {
            throw new RuntimeException(e);
        }
    }

    private void doFilter(final Iterator<Filter> filterIterator, final Runnable processRequestCycle) throws ServletException, IOException {
        if (filterIterator.hasNext()) {
            // execute the next filter and set up the chain for the next call to
            // this method
            final Filter filter = filterIterator.next();
            filter.doFilter(getRequest(), getResponse(), new FilterChain() {
                @Override
                public void doFilter(final ServletRequest request, final ServletResponse response) throws IOException, ServletException {
                    // recursively call this method again
                    FilteringWicketTester.this.doFilter(filterIterator, processRequestCycle);
                }
            });
        } else {
            // no more filters
            processRequestCycle.run();
        }
    }
}
