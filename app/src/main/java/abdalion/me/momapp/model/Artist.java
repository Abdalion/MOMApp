package abdalion.me.momapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Egon on 27/11/2016.
 */

public class Artist {

    private String name;
    @SerializedName("paints")
    private List<Paint> paintList;

    public String getName() {
        return name;
    }

    public List<Paint> getPaintList() {
        return paintList;
    }
}
