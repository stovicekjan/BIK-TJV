package cz.cvut.fit.tjv.cv.tvseries.restclient;

import javax.ws.rs.WebApplicationException;

/**
 * An exception that occurs during/after communication with the web service or
 * during inconsistency in data or because of incorrect request.
 * 
 * @author Ondrej Guth
 */
public class SeriesClientException extends RuntimeException {

    public SeriesClientException(WebApplicationException cause) {
        super(cause);
    }
    
}
