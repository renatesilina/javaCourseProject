package musicapplication.artistresults;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import musicapplication.SpotifyUrl;
import musicapplication.albumresults.Images;

/**
 * Class for storing information about an artist
 */
@JsonIgnoreProperties(value = {"external_urls", "href", "uri"})
public class ArtistItems {

    String[] genres;
    String id;
    Images[] images;
    String name;
    int popularity;
    String type;
    @JsonAlias("external_urls")
    SpotifyUrl spotifyUrl;
    Followers followers;

    public String getGenres() {
        String concatGenres = "";
        if (genres.length != 0) {
            concatGenres = concatGenres.concat("Artist genres: ");
            for (String s : genres) {
                concatGenres = concatGenres.concat(s + ", ");
            }
            concatGenres = concatGenres.substring(0, concatGenres.length() - 2);
        }
        return concatGenres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Images getImages() {
        return images[1];
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

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpotifyUrl() {
        return spotifyUrl.getSpotify();
    }

    public void setSpotifyUrl(SpotifyUrl spotifyUrl) {
        this.spotifyUrl = spotifyUrl;
    }

    public Followers getFollowers() {
        return followers;
    }

    public void setFollowers(Followers followers) {
        this.followers = followers;
    }
}
