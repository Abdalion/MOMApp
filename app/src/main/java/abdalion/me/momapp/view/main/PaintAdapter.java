package abdalion.me.momapp.view.main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import abdalion.me.momapp.Utils;
import abdalion.me.momapp.model.Paint;
import abdalion.me.momapp.view.detalle.DetalleActivity;

/**
 * Created by Egon on 27/11/2016.
 */

public class PaintAdapter extends RecyclerView.Adapter implements View.OnClickListener{

    private List<Paint> paintList;
    private Context context;
    private View.OnClickListener listener;


    public PaintAdapter(Context context) {
        this.context = context;
    }

    public void setPaintList(List<Paint> paintList) {
        this.paintList = paintList;
    }

    public List<Paint> getPaintList() {
        return paintList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View detalleView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_detalle, parent, false);
        PaintHolder paintHolder = new PaintHolder(detalleView);
        detalleView.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        listener.onClick(view);
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    private static class PaintHolder extends RecyclerView.ViewHolder{

        private ImageView imageThumbnail;

        public PaintHolder(View itemView) {
            super(itemView);
            imageThumbnail = (ImageView) itemView.findViewById(R.id.recycler_detalle_imagen);

        }

        public void bindProduct(Paint paint, Context context){
            Glide
                    .with(context)
                    .load(Uri.parse(Utils.PRE_URI + paint.getPath()))
                    .centerCrop()
                    .into(imageThumbnail);
        }
    }
}
