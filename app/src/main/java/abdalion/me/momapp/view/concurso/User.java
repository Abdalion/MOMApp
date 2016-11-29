package abdalion.me.momapp.view.concurso;

import android.net.Uri;

/**
 * Created by Egon on 29/11/2016.
 */

public class User {

    private String name;
    private String uid;

    public User(String name, String uid) {
        this.name = name;
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public String getUid() {
        return uid;
    }
}
