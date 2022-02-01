package musicapplication.trackresults;

import com.fasterxml.jackson.annotation.JsonAlias;
import musicapplication.albumresults.Albums;

/**
 * Class for storing tracks retrieved from spotify web API
 */
public class ListOfTracks {
    Albums tracks;

    public Albums getTracks() {
        return tracks;
    }

    public void setTracks(Albums tracks) {
        this.tracks = tracks;
    }
}
