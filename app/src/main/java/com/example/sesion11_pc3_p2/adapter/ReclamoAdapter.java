package com.example.sesion11_pc3_p2.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sesion11_pc3_p2.DetalleReclamoActivity;
import com.example.sesion11_pc3_p2.R;
import com.example.sesion11_pc3_p2.enums.ReclamoEnum;
import com.example.sesion11_pc3_p2.model.Reclamo;

import java.util.List;

public class ReclamoAdapter extends RecyclerView.Adapter<ReclamoAdapter.MyViewHolder>{

    private final Context context;
    private final List<Reclamo> reclamos;
    private final Activity activity;
    private Reclamo reclamo;

    public ReclamoAdapter(Activity activity, Context context, List<Reclamo> reclamos) {
        this.context = context;
        this.reclamos = reclamos;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row_reclamo, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if(reclamos.size() > 0) {
            reclamo = reclamos.get(position);
            holder.codigoTextView.setText(reclamo.getCodigo());
            holder.asuntoTextView.setText(reclamo.getAsunto());
            holder.descripcionTextView.setText(reclamo.getDescripcion());
            holder.estadoTextView.setText(reclamo.getEstado());
            holder.fechaCreacionTextView.setText(reclamo.getFechaCreacion());

            holder.mainLayout.setOnClickListener(v -> {
                Intent intent = new Intent(context, DetalleReclamoActivity.class);
                intent.putExtra(ReclamoEnum.KEY_NAME.getValue(), reclamo);
                activity.startActivityForResult(intent, 1);
            });
        }
    }

    @Override
    public int getItemCount() {
        return reclamos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView codigoTextView, asuntoTextView, descripcionTextView,
                 estadoTextView, fechaCreacionTextView;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            codigoTextView = itemView.findViewById(R.id.codigoReclamoTxt);
            asuntoTextView = itemView.findViewById(R.id.asuntoReclamoTxt);
            descripcionTextView = itemView.findViewById(R.id.descripcionReclamoTxt);
            estadoTextView = itemView.findViewById(R.id.estadoReclamoTxt);
            fechaCreacionTextView = itemView.findViewById(R.id.fechaCreacionReclamoTxt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
