package abdalion.me.momapp.view.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import abdalion.me.momapp.R;
import abdalion.me.momapp.controller.ArtistController;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        PaintAdapter paintAdapter = new PaintAdapter(getApplicationContext());
        recyclerView.setAdapter(paintAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        ArtistController artistController = new ArtistController();

        paintAdapter.setPaintList(artistController.getAllPaintings(this));
        paintAdapter.notifyDataSetChanged();

    }
}
