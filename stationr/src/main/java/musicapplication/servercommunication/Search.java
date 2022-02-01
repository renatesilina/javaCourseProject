package musicapplication.servercommunication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import musicapplication.albumresults.Albums;
import musicapplication.albumresults.Images;
import musicapplication.albumresults.Items;
import musicapplication.albumresults.ListOfAlbums;
import musicapplication.albumresults.albumtracks.AlbumTracks;
import musicapplication.artistresults.ListOfArtists;
import musicapplication.artistresults.toptracks.Album;
import musicapplication.artistresults.toptracks.ListOfTopTracks;
import musicapplication.playlistresults.CountriesTopPlaylist;
import musicapplication.trackresults.ListOfTracks;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Class for building up search uri's and retrieving information from spotify web API
 */
public class Search {
    RequestAccessToken requestToken = new RequestAccessToken();
    String name;
    int limit;

    public Search(String name, int limit) {
        this.name = name;
        this.limit = limit;
    }

    /**
     * Method for retrieving requested data from spotify web API
     *
     * @param uri search uri
     * @return information for searched item
     */
    public HttpResponse<String> getResponse(URI uri) {
        // Send a get request to spotify api
        HttpRequest request;
        HttpResponse<String> response = null;
        try {
            request = HttpRequest.newBuilder(uri)
                    .GET()
                    .setHeader("Authorization", "Bearer " + requestToken.getToken())
                    .setHeader("Content-Type", "application/json")
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            //System.out.println("Status code: "+response.statusCode());
            //System.out.println("Response body: \n"+response.body());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Method for retrieving information about albums
     *
     * @return an object with an array of albums
     */
    public ListOfAlbums getAlbums() {

        ListOfAlbums albumsList = null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            HttpResponse<String> response = getResponse(new URI("https://api.spotify.com/v1/search?q=" + name + "&type=album&limit=" + limit));

            albumsList = mapper.readValue(response.body(), ListOfAlbums.class);
            //System.out.println("ALBUMS: " + response.body());
        } catch (URISyntaxException | JsonProcessingException e) {
            e.printStackTrace();
        }
        return albumsList;
    }

    /**
     * Method for retrieving an album track list
     *
     * @return an object with an array of album tracks
     */
    public AlbumTracks getAlbumTracks() {

        AlbumTracks tracks = new AlbumTracks();
        ObjectMapper mapper = new ObjectMapper();

        try {
            HttpResponse<String> response = getResponse(new URI("https://api.spotify.com/v1/albums/" + name + "/tracks"));

            tracks = mapper.readValue(response.body(), AlbumTracks.class);
            //System.out.println("ALBUM TRACKS: " + response.body());
        } catch (URISyntaxException | JsonProcessingException e) {
            e.printStackTrace();
        }
        return tracks;
    }

    /**
     * Method for searching for artists
     *
     * @return an object with an array of artists
     */
    public ListOfArtists getArtists() {

        ListOfArtists artistsList = null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            HttpResponse<String> response = getResponse(new URI("https://api.spotify.com/v1/search?q=" + name + "&type=artist&limit=" + limit));

            artistsList = mapper.readValue(response.body(), ListOfArtists.class);
            //System.out.println("ARTISTS: " + response.body());
        } catch (URISyntaxException | JsonProcessingException e) {
            e.printStackTrace();
        }
        return artistsList;
    }

    /**
     * Method for retrieving an artist top songs
     *
     * @return an object with an array of an artist top songs
     */
    public ListOfTopTracks getTopSongs() {

        ListOfTopTracks artistTopTracks = null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            HttpResponse<String> response = getResponse(new URI("https://api.spotify.com/v1/artists/" + name + "/top-tracks?market=FI"));

            artistTopTracks = mapper.readValue(response.body(), ListOfTopTracks.class);
            //System.out.println(response.body());
        } catch (URISyntaxException | JsonProcessingException e) {
            e.printStackTrace();
        }
        return artistTopTracks;
    }

    /**
     * Method for retrieving tracks
     *
     * @return an object with an array of requested tracks
     */
    public ListOfTracks getTracks() {

        ListOfTracks tracks = null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            HttpResponse<String> response = getResponse(new URI("https://api.spotify.com/v1/search?q=" + name + "&type=track&limit=" + limit));

            tracks = mapper.readValue(response.body(), ListOfTracks.class);
            //System.out.println("TRACKS: " + response.body());
        } catch (URISyntaxException | JsonProcessingException e) {
            e.printStackTrace();
        }
        return tracks;
    }

    /**
     * Method for retrieving a country's top50 playlist
     *
     * @return an object with information about requested playlist
     */
    public CountriesTopPlaylist getTopListsCountry() {

        CountriesTopPlaylist playlist = new CountriesTopPlaylist();
        ObjectMapper mapper = new ObjectMapper();

        try {
            HttpResponse<String> response = getResponse(new URI("https://api.spotify.com/v1/playlists/" + name));

            playlist = mapper.readValue(response.body(), CountriesTopPlaylist.class);
            //System.out.println("TOPLISTS: " + response.body());
        } catch (URISyntaxException | JsonProcessingException e) {
            e.printStackTrace();
        }
        return playlist;
    }

    /**
     * Method for retrieving playlists
     * Used for searching for countries top50 playlists
     */
    public void daylyTopLists() {
        try {
            HttpResponse<String> response = getResponse(new URI("https://api.spotify.com/v1/search?q=top50%2050uae&type=playlist"));

            //playlist = mapper.readValue(response.body(), CountriesTopSongs.class);
            //System.out.println("DAYLY TOPLISTS: " + response.body());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Search search1 = new Search("life", 2);
        search1.getArtists();
//        ListOfTracks tracks = search1.getTracks();
//        Albums track = tracks.getTracks();
//        Items[] trackItems = track.getItems();
//        Images[] trackImages = new Images[3];
//        int counter = 0;
//        for (Items i : trackItems) {
//            String name = i.getName();
//            String artists = i.getArtists();
//            System.out.println("Track: " + name + "\n" + "Artists: " + artists);
//            Album trackAlbum = i.getAlbum();
//            trackImages = trackAlbum.getImages();
//        }
//        for (Images img : trackImages) {
//            System.out.println("Image: " + img.getUrl());
//        }
//        Search search = new Search("37i9dQZEVXbK4fwx2r07XW", 1);
//        CountriesTopPlaylist playlist = search.getTopListsCountry();
//        System.out.println(playlist.toString());
//
//        Search search1 = new Search("eminem", 1);
//        search1.daylyTopLists();

    }
}
