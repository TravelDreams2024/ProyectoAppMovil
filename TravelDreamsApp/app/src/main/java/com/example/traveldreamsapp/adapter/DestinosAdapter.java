package com.example.traveldreamsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.traveldreamsapp.R;
import com.example.traveldreamsapp.models.Destinos;

import java.util.List;

public class DestinosAdapter extends RecyclerView.Adapter<DestinosAdapter.ViewHolder> {

    private final List<Destinos> destinos;
    private final Context context;

    public DestinosAdapter(List<Destinos> destinos, Context context) {
        this.destinos = destinos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_destino, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_nombre_Destino.setText(destinos.get(position).getNombre_Destino());
        Glide.with(context).load(destinos.get(position).getImage()).into(holder.iv_image);

    }

    @Override
    public int getItemCount() {
        return destinos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final Button btn_comprar;
        private final ImageView iv_image;
        private final TextView tv_nombre_Destino;
        private final TextView tv_descripcion;
        private final TextView tv_precio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_image = itemView.findViewById(R.id.iv_image);
            tv_nombre_Destino = itemView.findViewById(R.id.tv_nombre_Destino);
            tv_descripcion = itemView.findViewById(R.id.tv_descripcion);
            tv_precio = itemView.findViewById(R.id.tv_precio);
            btn_comprar = itemView.findViewById(R.id.btn_comprar);
        }
    }
}
