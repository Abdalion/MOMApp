package abdalion.me.momapp.dao;

import android.content.Context;
import android.content.res.AssetManager;
import android.widget.Toast;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import abdalion.me.momapp.model.ArtistContainer;

/**
 * Created by Egon on 27/11/2016.
 */

public class ArtistDAO {

    public ArtistContainer getArtistContainer(Context context) {
        Gson gson = new Gson();
        AssetManager manager = context.getAssets();
        InputStream artistsJson = null;
        try {
            artistsJson = manager.open("artisPaints.json");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        BufferedReader bufferReaderIn = new BufferedReader(new InputStreamReader(artistsJson));
        ArtistContainer artistContainer = gson.fromJson(bufferReaderIn, ArtistContainer.class);
        return artistContainer;
    }

}
