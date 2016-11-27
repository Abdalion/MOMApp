package abdalion.me.momapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Egon on 27/11/2016.
 */

public class ArtistContainer {

    @SerializedName("artists")
    private List<Artist> artistList;

    public List<Artist> getArtistList() {
        return artistList;
    }
}
