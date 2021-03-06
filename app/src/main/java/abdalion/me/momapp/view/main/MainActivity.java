package abdalion.me.momapp.view.main;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieSyncManager;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

import abdalion.me.momapp.view.concurso.Concurso;
import io.fabric.sdk.android.Fabric;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import abdalion.me.momapp.R;
import abdalion.me.momapp.controller.ArtistController;
import abdalion.me.momapp.model.Artist;
import abdalion.me.momapp.model.Paint;
import abdalion.me.momapp.view.detalle.DetalleActivity;

public class MainActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "7BLPD9g4GGf2GShHeoKJPtgaM";
    private static final String TWITTER_SECRET = "DzCMX4P3AteivNKcwZ6c4OETf0vnhmwov38ma8cfebE8JZmEbc";


    RecyclerView recyclerView;
    PaintAdapter paintAdapter;
    ArtistController artistController = new ArtistController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        paintAdapter = new PaintAdapter(getApplicationContext());
        recyclerView.setAdapter(paintAdapter);
        paintAdapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Paint> paintList = paintAdapter.getPaintList();
                Paint paint = paintList.get(recyclerView.getChildAdapterPosition(view));
                Intent intent = new Intent(MainActivity.this, DetalleActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", paint.getName());
                bundle.putString("path", paint.getPath());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        paintAdapter.setPaintList(artistController.getAllPaintings(this));
        paintAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_concurso) {
            startActivity(new Intent(MainActivity.this, Concurso.class));
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
    }
}
