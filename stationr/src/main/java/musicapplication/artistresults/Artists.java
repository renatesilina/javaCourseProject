package musicapplication.artistresults;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * Class for storing an array with information about an artist
 */
@JsonIgnoreProperties(value = {"href", "limit", "next", "offset", "previous", "total"})
public class Artists {
    ArtistItems[] items;

    public ArtistItems[] getItems() {
        return items;
    }

    public void setItems(ArtistItems[] items) {
        this.items = items;
    }
}
