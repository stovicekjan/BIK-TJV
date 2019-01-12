package cz.cvut.fit.tjv.cv.tvseries.restclient;

import cz.cvut.fit.tjv.cv.tvseries.service.SerieDTO;

/**
 * Client for the TV Series RESTful web service for the Serie entity type.
 * Implemented as a singleton.
 * 
 * @author Ondrej Guth
 */
public class SerieClient extends AbstractCRUDClient<Long, SerieDTO> {
    
    /** The singleton instance. */
    private final static SerieClient INSTANCE = new SerieClient();
    
    /** Initialize this instance. Due to singleton, it must be private. */
    private SerieClient() {
        super("series", SerieDTO.class, SerieDTO[].class);
    }
    
    /**
     * The singleton instance getter. 
     * @return the singleton instance
     */
    public static SerieClient getInstance() {
        return INSTANCE;
    }
    
}
