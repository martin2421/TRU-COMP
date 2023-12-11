package com.example.bulkszn;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class Food_Adapter extends RecyclerView.Adapter<Food_Adapter.View_Holder> {
    Context context;
    ArrayList<Food_modal> arrayList;
    double total = 0;

    private MyListener listener;

    public void setListener(MyListener listener) {
        this.listener = listener;
    }


    Food_Adapter(Context context, ArrayList<Food_modal> arrayList) {
        this.context = context;
        this.arrayList = arrayList;

    }

    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Inflate the view
        View view = LayoutInflater.from(context).inflate(R.layout.food_row, parent, false);
        View_Holder viewHolder = new View_Holder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder holder, int position) {

        // Bind the data
        holder.image.setImageResource(arrayList.get(position).img);
        holder.name.setText(arrayList.get(position).foodName);
        holder.protein_quantity.setText(arrayList.get(position).proteinQuantity);
        holder.serving.setText(arrayList.get(position).serving);

        SharedPreferences sharedPreferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        int selectedPosition = holder.getAdapterPosition();

        ProfileFragment fragment = new ProfileFragment();
        Bundle bundle = new Bundle();

        // Add protein
        holder.add.setOnClickListener(view -> {

            int currentServing = Integer.parseInt(arrayList.get(selectedPosition).serving); // parse serving member from object
            currentServing += 1; // increase the serving by 1

            String proteinStr = arrayList.get(selectedPosition).proteinQuantity; // get the string for protein quantity
            proteinStr = proteinStr.replace("Protein: ", ""); // removing text
            proteinStr = proteinStr.replace("g", ""); // removing text
            double protein = Double.parseDouble(proteinStr); // parsing to double

            arrayList.get(selectedPosition).serving = String.valueOf(currentServing); // serving = new serving
            holder.serving.setText(String.valueOf(currentServing)); // update the serving textview

            total += protein;
            editor.putFloat("total", Float.parseFloat(String.valueOf(total)));
            editor.apply();

            if (listener != null) {
                listener.onButtonClicked("Total: " + String.format("%.1f", total) + "g");
            }

        });

        // Remove protein
        holder.remove.setOnClickListener(view -> {

            int currentServing = Integer.parseInt(arrayList.get(selectedPosition).serving); // parse serving member from object
            currentServing -= 1; // increase the serving by 1

            if (currentServing <= 0) {
                currentServing = 0; // reset to 0
            }

            String proteinStr = arrayList.get(selectedPosition).proteinQuantity; // get the string for protein quantity
            proteinStr = proteinStr.replace("Protein: ", ""); // removing text
            proteinStr = proteinStr.replace("g", ""); // removing text
            double protein = Double.parseDouble(proteinStr); // parsing to double

            arrayList.get(selectedPosition).serving = String.valueOf(currentServing); // serving = new serving

            if (currentServing <= 0) {
                holder.serving.setText(String.valueOf(0));
            } else {
                holder.serving.setText(String.valueOf(currentServing)); // update the serving textview
            }

            total -= protein;
            editor.putFloat("total", Float.parseFloat(String.valueOf(total)));
            editor.apply();

            if (listener != null) {

                if (total <= 0) {
                    listener.onButtonClicked("Total: 0g");
                    total = 0;
                } else {
                    listener.onButtonClicked("Total: " + String.format("%.1f", total) + "g");
                }

            }

        });

        if (bundle != null) {
            Log.d("Bundle (Adapter)", "NOT NULL");
        } else {
            Log.d("Bundle (Adapter)", "NULL");
        }

    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class View_Holder extends RecyclerView.ViewHolder {
        TextView name, protein_quantity, serving;
        ImageView image;

        Button add, remove;

        public View_Holder(@NonNull View itemView) {

            super(itemView);

            // Get views in the item of the view holder
            name = itemView.findViewById(R.id.tv_food_name);
            protein_quantity = itemView.findViewById(R.id.tv_protein_amount);
            image = itemView.findViewById(R.id.image);
            serving = itemView.findViewById(R.id.tv_serving);
            add = itemView.findViewById(R.id.btn_add);
            remove = itemView.findViewById(R.id.btn_remove);

        }
    }


}
