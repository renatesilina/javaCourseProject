package musicapplication.playlistresults;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * Class for storing an array with information about a playlist songs
 */
@JsonIgnoreProperties(value = {"href", "limit", "next", "offset", "previous", "total"})
public class PlaylistSongs {
    TrackItems[] items;

    public TrackItems[] getItems() {
        return items;
    }

    public void setItems(TrackItems[] items) {
        this.items = items;
    }
}
