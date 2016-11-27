package abdalion.me.momapp.view;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

import abdalion.me.momapp.R;
import abdalion.me.momapp.model.Paint;

/**
 * Created by Egon on 27/11/2016.
 */

public class PaintAdapter extends RecyclerView.Adapter {

    private List<Paint> paintList;
    private Context context;
    private static final String PRE_URI = "file:///android_asset/";

    public PaintAdapter(Context context) {
        this.context = context;
    }

    public void setPaintList(List<Paint> paintList) {
        this.paintList = paintList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View detalleView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_detalle, parent, false);
        PaintHolder paintHolder = new PaintHolder(detalleView);
        return paintHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Paint paint = paintList.get(position);
        PaintHolder paintHolder = (PaintHolder) holder;
        paintHolder.bindProduct(paint, context);
    }

    @Override
    public int getItemCount() {
        return paintList.size();
    }

    private static class PaintHolder extends RecyclerView.ViewHolder{

        private ImageView imageThumbnail;

        public PaintHolder(View itemView) {
            super(itemView);
            imageThumbnail = (ImageView) itemView.findViewById(R.id.recycler_detalle_imagen);

        }

        public void bindProduct(Paint paint, Context context){
            Log.d("Paint", paint.getName());
            Glide
                    .with(context)
                    .load(Uri.parse(PRE_URI + paint.getPath()))
                    .centerCrop()
                    .into(imageThumbnail);
        }
    }
}
