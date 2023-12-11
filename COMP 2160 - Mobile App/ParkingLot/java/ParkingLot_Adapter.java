package com.example.parkinglot;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ParkingLot_Adapter extends RecyclerView.Adapter<ParkingLot_Adapter.View_Holder> {
   Context context;
   ArrayList<ParkingLot_Model> arrayList;

    ParkingLot_Adapter(Context context, ArrayList<ParkingLot_Model> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }
    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.parkinglot_row,parent,false);
        View_Holder viewHolder = new View_Holder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder holder, int position) {

        holder.name.setText(arrayList.get(position).name);
        holder.itemView.setOnClickListener(view -> {
            int selectedPosition= holder.getAdapterPosition();
            Intent intent=new Intent(context, MainActivity2.class);
                   intent.putExtra("name", arrayList.get(selectedPosition).name);
                    context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class View_Holder extends RecyclerView.ViewHolder {
        TextView name;
        public View_Holder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.contact_name);

        }
    }


}
