package abdalion.me.momapp.view.detalle;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.tweetcomposer.Card;
import com.twitter.sdk.android.tweetcomposer.ComposerActivity;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import java.io.File;

import abdalion.me.momapp.R;
import abdalion.me.momapp.Utils;
import abdalion.me.momapp.controller.ArtistController;
import abdalion.me.momapp.model.Artist;
import abdalion.me.momapp.view.login.LoginActivity;
import abdalion.me.momapp.view.main.MainActivity;

public class DetalleActivity extends AppCompatActivity {

    ArtistController artistController = new ArtistController();
    String name;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("name");
        path = bundle.getString("path");
        TextView textTitulo = (TextView) findViewById(R.id.activity_detalle_titulo);
        textTitulo.setText(textTitulo.getText() + name);

        TextView textAutor = (TextView) findViewById(R.id.activity_detalle_autor);
        textAutor.setText(textAutor.getText() + (artistController.getArtistByPaint(name, this).getName()+""));

        ImageView imageView = (ImageView) findViewById(R.id.activity_detalle_imagen);
        Glide
                .with(getApplicationContext())
                .load(Uri.parse(Utils.PRE_URI + path))
                .centerCrop()
                .into(imageView);

        Button shareButton = (Button) findViewById(R.id.acitivity_detalle_botonShare);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Twitter.getInstance().core.getSessionManager().getActiveSession() != null) {
                    File myImageFile = new File(Utils.PRE_URI + path);
                    Uri myImageUri = Uri.fromFile(myImageFile);
                    TweetComposer.Builder builder = new TweetComposer.Builder(DetalleActivity.this)
                            .text("")
                            .image(myImageUri);
                    builder.show();

                    //Esta verga necesita algun tipo de autorizacion de Google Play Services para funcionar.
/*                    final TwitterSession session = TwitterCore.getInstance().getSessionManager()
                            .getActiveSession();
                    final Card card = new Card.AppCardBuilder(DetalleActivity.this)
                            .imageUri(Uri.parse(Utils.PRE_URI + path))
                            .build();
                    final Intent intent = new ComposerActivity.Builder(DetalleActivity.this)
                            .session(session)
                            .card(card)
                            .hashtags("#MOMApp", "#Fashion")
                            .createIntent();
                    startActivity(intent);*/
                }
                else {
                    startActivity(new Intent(DetalleActivity.this, LoginActivity.class));
                }
            }
        });

    }


}
