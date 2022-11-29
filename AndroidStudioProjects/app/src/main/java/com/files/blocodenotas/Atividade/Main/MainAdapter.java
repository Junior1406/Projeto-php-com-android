package com.files.blocodenotas.Atividade.Main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.files.blocodenotas.Modelo.Nota;
import com.files.blocodenotas.R;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.RecylerViewerAdapter>{

    private Context context;
    private List<Nota> notas;
    private ItemClickListener itemClickListener;

    public MainAdapter(Context context, List<Nota> notas, ItemClickListener itemClickListener) {
        this.context = context;
        this.notas = notas;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecylerViewerAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_note,
                parent, false);
        return new RecylerViewerAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecylerViewerAdapter holder, int position) {
        Nota nota = notas.get(position);
        holder.tv_titulo.setText(nota.getTitulo());
        holder.tv_nota.setText(nota.getNota());
        holder.tv_data.setText(nota.getData());
        holder.card_item.setCardBackgroundColor(nota.getCor());
    }

    @Override
    public int getItemCount() {
        return notas.size();
    }

    class RecylerViewerAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tv_titulo, tv_nota, tv_data;
        CardView card_item;
        ItemClickListener itemClickListener;
        public RecylerViewerAdapter(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);


            tv_titulo = itemView.findViewById(R.id.titulo);
            tv_nota = itemView.findViewById(R.id.nota);
            tv_data = itemView.findViewById(R.id.data);
            card_item = itemView.findViewById(R.id.card_item);

            this.itemClickListener = itemClickListener;
            card_item.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemclick(v, getAdapterPosition());
        }
    }

    public interface ItemClickListener{
        void onItemclick(View view, int position);
    }
}
