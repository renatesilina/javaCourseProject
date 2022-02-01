package musicapplication.playlistresults;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import musicapplication.albumresults.Artist;
import musicapplication.albumresults.Images;
import musicapplication.artistresults.toptracks.Album;

/**
 * Class for storing information about a track
 */
@JsonIgnoreProperties(value = {"available_markets", "disc_number", "disc_number", "episode",
        "explicit", "external_ids", "external_urls", "href", "is_local", "preview_url",
        "track", "type", "uri", "track_number"})
public class SingleTrack {
    Album album;
    Artist[] artists;
    @JsonAlias("duration_ms")
    int duration;
    String name;
    int popularity;
    String id;

    public Album getAlbum() {
        return album;
    }

    public String getAlbumImage() {
        return album.getImages()[0].getUrl();
    }

    public void setAlbum(Album album) {
        this.album = album;
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

    public String getArtists() {
        String names = "";
        for (Artist s : artists) {
            names = names.concat(s.getName() + ", ");
        }
        names = names.substring(0, names.length() - 2);
        return names;
    }

    public void setArtists(Artist[] artists) {
        this.artists = artists;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
