package musicapplication.artistresults.toptracks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import musicapplication.albumresults.Artist;
import musicapplication.albumresults.Images;

/**
 * Class for storing images and name of an album
 */
@JsonIgnoreProperties(value = {"album_type", "artists", "available_markets", "external_urls", "href", "id", "release_date", "release_date_precision", "total_tracks", "type", "uri"})
public class Album {
    Images[] images;
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Images[] getImages() {
        return images;
    }

    public void setImages(Images[] images) {
        this.images = images;
    }
}
