package cz.cvut.fit.tjv.cv.tvseries.service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Definition of a RESTful web service endpoint (part of URL).
 * @author Ondrej Guth
 */
@ApplicationPath("/seriesresources")
public class SeriesEndpoint extends Application {

    /**
     * Define a set of classes that are part of this endpoint.
     * @return Set of classes being part of this endpoint
     */
    @Override
    public Set<Class<?>> getClasses() {
        return Stream
                .of(SerieResource.class, PersonResource.class)
                .collect(Collectors.toSet());
    }
    
}
