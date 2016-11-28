package abdalion.me.momapp.view.detalle;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import abdalion.me.momapp.R;
import abdalion.me.momapp.Utils;
import abdalion.me.momapp.controller.ArtistController;
import abdalion.me.momapp.model.Artist;
import abdalion.me.momapp.view.main.MainActivity;

public class DetalleActivity extends AppCompatActivity {

    ArtistController artistController = new ArtistController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        String path = bundle.getString("path");
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

    }


}
