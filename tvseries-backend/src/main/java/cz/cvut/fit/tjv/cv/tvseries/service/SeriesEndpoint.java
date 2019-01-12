package cz.cvut.fit.tjv.cv.tvseries.service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/resources")
public class SeriesEndpoint extends Application{
    
    @Override
    public Set<Class<?>> getClasses() {
        return Stream.of(PersonResource.class, SerieResource.class).collect(Collectors.toSet());
    }
}
