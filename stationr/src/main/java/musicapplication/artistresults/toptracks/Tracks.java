package musicapplication.artistresults.toptracks;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import musicapplication.albumresults.Images;

/**
 * Class for storing information about a track
 */
@JsonIgnoreProperties(value = {"artists", "disc_number", "explicit", "external_ids", "external_urls", "href", "id", "is_local", "is_playable", "preview_url", "track_number", "type", "uri"})
public class Tracks {
    Album album;
    @JsonAlias("duration_ms")
    int duration;
    String name;
    int popularity;

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getDurationString() {
        int minutes = (duration / 1000) / 60;
        int seconds = (duration / 1000) % 60;
        return "" + minutes + "." + seconds;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public String getAlbumImage() {
        Images[] albumImage = album.getImages();
        return albumImage[2].getUrl();
    }

    public String getAlbumName() {
        return album.getName();
    }
}
