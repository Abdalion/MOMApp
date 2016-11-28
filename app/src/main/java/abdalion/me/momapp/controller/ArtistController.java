package abdalion.me.momapp.controller;

import android.content.Context;

import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;

import abdalion.me.momapp.dao.ArtistDAO;
import abdalion.me.momapp.model.Artist;
import abdalion.me.momapp.model.ArtistContainer;
import abdalion.me.momapp.model.Paint;

/**
 * Created by Egon on 27/11/2016.
 */

public class ArtistController {

    public List<Artist> getArtists(Context context) {
        List<Artist> artistList = new ArrayList<>();
        ArtistDAO artistDAO = new ArtistDAO();
        artistList.addAll(artistDAO.getArtistContainer(context).getArtistList());
        return artistList;
    }

    public List<Paint> getPaintByArtistName(String artistName, Context context) {
        List<Paint> paintList = new ArrayList<>();
        ArtistDAO artistDAO = new ArtistDAO();
        for(Artist artist : artistDAO.getArtistContainer(context).getArtistList()) {
            if(artist.getName().equalsIgnoreCase(artistName)) {
                paintList.addAll(artist.getPaintList());
            }
        }
        return paintList;
    }

    public Artist getArtistByPaint(String paintName, Context context) {
        ArtistDAO artistDAO = new ArtistDAO();
        for(Artist artist : artistDAO.getArtistContainer(context).getArtistList()) {
            for(Paint paint : artist.getPaintList()) {
                if(paint.getName().equalsIgnoreCase(paintName)) {
                    return artist;
                }
            }
        }
        return null;
    }

    public List<Paint> getAllPaintings(Context context) {
        List<Paint> paintList = new ArrayList<>();
        ArtistDAO artistDAO = new ArtistDAO();
        ArtistContainer artistContainer = artistDAO.getArtistContainer(context);
        for(Artist artist : artistContainer.getArtistList()) {
            paintList.addAll(artist.getPaintList());
        }
        return paintList;
    }
}
