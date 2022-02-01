package musicapplication.albumresults.albumtracks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import musicapplication.albumresults.Items;

/**
 * Class for storing item array for songs from an album
 * Information retrieved from spotify web API
 */
@JsonIgnoreProperties(value = {"href", "limit", "next", "offset", "previous", "total"})
public class AlbumTracks {
    Items[] items;

    public Items[] getItems() {
        return items;
    }

    public void setItems(Items[] items) {
        this.items = items;
    }
}
