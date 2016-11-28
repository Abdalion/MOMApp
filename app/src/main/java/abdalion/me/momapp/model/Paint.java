package abdalion.me.momapp.model;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Egon on 27/11/2016.
 */

public class Paint {

    private String name;
    @SerializedName("image")
    private String path;

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }


}
