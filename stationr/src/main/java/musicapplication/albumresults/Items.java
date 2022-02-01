package musicapplication.albumresults;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import musicapplication.SpotifyUrl;
import musicapplication.artistresults.toptracks.Album;

import java.util.Date;

/**
 * Class for storing information about a song or an album
 */
@JsonIgnoreProperties(value = {"href", "disc_number", "available_markets", "explicit", "external_ids", "href", "release_date_precision", "is_local", "preview_url", "track_number", "type"})
public class Items {
    // Album data fields
    @JsonAlias("album_type")
    String albumType;
    Artist[] artists;
    String id;
    Images[] images;
    String name;
    @JsonAlias("release_date")
    Date releaseDate;
    @JsonAlias("total_tracks")
    int totalTracks;
    String type;
    String uri;
    @JsonAlias("external_urls")
    SpotifyUrl spotifyUrl;
    // Extra fields for tracks data
    @JsonAlias("duration_ms")
    int duration;
    int popularity;
    Album album;

    public String getAlbumType() {
        return albumType;
    }

    public void setAlbumType(String albumType) {
        this.albumType = albumType;
    }

    public String getArtists() {
        // Return all artists in one string
        String concatArtists = "";
        // Modify the string
        for (Artist a : artists) {
            concatArtists = concatArtists.concat(a.getName() + " & ");
        }
        concatArtists = concatArtists.substring(0, concatArtists.length() - 3);
        return concatArtists;
    }

    public void setArtists(Artist[] artists) {
        this.artists = artists;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Images[] getImages() {
        return images;
    }

    public void setImages(Images[] images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTotalTracks() {
        // Return as string for easier use
        return "" + totalTracks;
    }

    public void setTotalTracks(int totalTracks) {
        this.totalTracks = totalTracks;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    // Extra getters and setters for tracks data
    public int getDuration() {
        return duration;
    }

    public String getDurationString() {
        int minutes = (duration / 1000) / 60;
        int seconds = (duration / 1000) % 60;
        return "" + minutes + "." + seconds;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public String getSpotifyUrl() {
        return spotifyUrl.getSpotify();
    }

    public void setSpotifyUrl(SpotifyUrl spotifyUrl) {
        this.spotifyUrl = spotifyUrl;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
