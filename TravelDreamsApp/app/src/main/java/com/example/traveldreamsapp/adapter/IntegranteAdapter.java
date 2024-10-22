package com.example.traveldreamsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.traveldreamsapp.Integrante;
import com.example.traveldreamsapp.R;

import java.util.List;

public class IntegranteAdapter extends RecyclerView.Adapter<IntegranteAdapter.IntegranteViewHolder> {
    private final List<Integrante> integranteList;
    private final Context context;

    public IntegranteAdapter(Context context, List<Integrante> integranteList) {
        this.context = context;
        this.integranteList = integranteList;
    }

    @NonNull
    @Override
    public IntegranteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_integrante, parent, false);
        return new IntegranteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IntegranteViewHolder holder, int position) {
        Integrante integrante = integranteList.get(position);

        holder.nombreTextView.setText(integrante.getNombreApellido());
        holder.githubTextView.setText(integrante.getGithub());
        holder.linkedinTextView.setText(integrante.getLinkedin());
        holder.rolTextView.setText("Rol ID: " + integrante.getIdRol());

        // Usar Glide para cargar la imagen desde la URL
        Glide.with(context)
                .load(integrante.getImagen())
                .into(holder.imagenImageView);
    }

    @Override
    public int getItemCount() {
        return integranteList.size();
    }

    public static class IntegranteViewHolder extends RecyclerView.ViewHolder {
        TextView nombreTextView, githubTextView, linkedinTextView, rolTextView;
        ImageView imagenImageView;

        public IntegranteViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.text_nombre);
            githubTextView = itemView.findViewById(R.id.text_github);
            linkedinTextView = itemView.findViewById(R.id.text_linkedin);
            rolTextView = itemView.findViewById(R.id.text_rol);
            imagenImageView = itemView.findViewById(R.id.imagen_integrante);
        }
    }
}
