package abdalion.me.momapp.view.main;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

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

    RecyclerView recyclerView;
    PaintAdapter paintAdapter;
    ArtistController artistController = new ArtistController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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


}
