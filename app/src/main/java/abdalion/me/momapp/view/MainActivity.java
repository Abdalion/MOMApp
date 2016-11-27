package abdalion.me.momapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import abdalion.me.momapp.R;
import abdalion.me.momapp.controller.ArtistController;
import abdalion.me.momapp.model.Artist;
import abdalion.me.momapp.model.ArtistContainer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
