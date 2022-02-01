package musicapplication.views.songs;

import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import musicapplication.SearchError;
import musicapplication.albumresults.Albums;
import musicapplication.albumresults.Items;
import musicapplication.artistresults.toptracks.Album;
import musicapplication.servercommunication.Search;
import musicapplication.trackresults.ListOfTracks;
import musicapplication.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

/**
 * Class for showing information about songs
 */
@PageTitle("Songs")
@Route(value = "songs", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class SongsView extends VerticalLayout {

    String currentSearch = "";

    public SongsView() {
        setPadding(true);
        searchTracks();
    }

    /**
     * Method for making user input components
     * Passes user input to other method
     */
    private void searchTracks() {
        TextField name = new TextField("Search for songs");
        name.setWidth("280px");

        Button search = new Button("Search");
        search.setAutofocus(true);
        search.setWidth("284px");
        search.addClickListener(e -> getInfo(name.getValue()));

        setAlignItems(Alignment.CENTER);
        add(name, search);
    }

    /**
     * Method for retrieving information about songs
     * Modifies user input string
     * Shows error if no information is found
     *
     * @param name users input string
     */
    private void getInfo(String name) {
        currentSearch = name.replaceAll(" ", "%20");
        Search search = new Search(currentSearch, 18);

        ListOfTracks trackList = search.getTracks();
        // Check if search was successful
        if (trackList != null) {
            Albums tracks = trackList.getTracks();
            Items[] trackItems = tracks.getItems();

            int index = 0, limit = 9;
            showTracks(trackItems, index, limit);
            Button more = new Button("Show more");
            add(more);
            more.addClickListener(e -> {
                showTracks(trackItems, index + 9, limit + 9);
                remove(more);
            });
        } else {
            // Show search error
            SearchError error = new SearchError();
            error.showError();
        }
    }

    /**
     * Method for going through array of songs
     * Adding few song components to view at once
     *
     * @param trackItems array with songs
     * @param indexFrom  beginning index for going through array
     * @param limit      end index for going through array
     */
    private void showTracks(Items[] trackItems, int indexFrom, int limit) {
        // New array for component making
        Items[] someTracks = new Items[3];
        // Item counter
        int itemCounter = 0;


        for (int i = indexFrom; i < limit; i++) {
            // Put an item into the new array
            someTracks[itemCounter] = trackItems[i];
            itemCounter++;
            if (itemCounter == 3) {
                // Add items to page
                makeComponents(someTracks);
                someTracks = new Items[3];
                itemCounter = 0;
            }
        }
    }

    /**
     * Method for making components with information about songs
     *
     * @param someTracks an array with some retrieved songs
     */
    private void makeComponents(Items[] someTracks) {
        HorizontalLayout hLayout = new HorizontalLayout();
        hLayout.setWidthFull();

        for (Items i : someTracks) {
//            Album album = i.getAlbum();
//            Image img = new Image(album.getImages()[1].getUrl(), album.getImages()[1].getUrl());
//
//            H3 name = new H3(i.getName());
//            H5 artist = new H5("Artists: " + i.getArtists());

            IFrame track = new IFrame("https://open.spotify.com/embed/track/" + i.getId());
            track.setWidth("300px");
            track.setHeight("380px");
            track.getStyle().set("border", "0px");
            track.setAllow("transparency");
            track.setAllow("encrypted-media");

            //img, name, artist
            VerticalLayout vLayout = new VerticalLayout(track);
            hLayout.add(vLayout);
            //hLayout.setAlignItems(Alignment.CENTER);
        }
        hLayout.setJustifyContentMode(JustifyContentMode.EVENLY);
        add(hLayout);
    }

}
