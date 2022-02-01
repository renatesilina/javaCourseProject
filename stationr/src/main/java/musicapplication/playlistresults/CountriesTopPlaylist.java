package musicapplication.playlistresults;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import musicapplication.albumresults.Images;

/**
 * Class for storing information about a country's top50 playlist
 */
@JsonIgnoreProperties(value = {"collaborative", "external_urls", "followers", "href", "id", "owner", "primary_color", "public", "snapshot_id", "type", "uri"})
public class CountriesTopPlaylist {
    String name;
    String description;
    Images[] images;
    PlaylistSongs tracks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImages() {
        return images[0].getUrl();
    }

    public void setImages(Images[] images) {
        this.images = images;
    }

    public PlaylistSongs getTracks() {
        return tracks;
    }

    public void setTracks(PlaylistSongs tracks) {
        this.tracks = tracks;
    }
}
