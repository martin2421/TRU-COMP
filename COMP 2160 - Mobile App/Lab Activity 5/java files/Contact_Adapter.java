package com.example.contacts;

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

public class Contact_Adapter extends RecyclerView.Adapter<Contact_Adapter.View_Holder> {
    Context context;
    ArrayList<Contact_modal> arrayList;

    Contact_Adapter(Context context, ArrayList<Contact_modal> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the view
        View view = LayoutInflater.from(context).inflate(R.layout.contact_row, parent, false);
        View_Holder viewHolder = new View_Holder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder holder, int position) {
        // Bind the data
        holder.image.setImageResource(arrayList.get(position).img);
        holder.name.setText(arrayList.get(position).name);
        holder.number.setText(arrayList.get(position).number);
        holder.email.setText(arrayList.get(position).email);


        // Click listener on a complete row
        holder.itemView.setOnClickListener(view -> {
            int selectedPosition = holder.getAdapterPosition();
            Intent intent = new Intent(context, MainActivity2.class);
            intent.putExtra("image", arrayList.get(selectedPosition).img);
            intent.putExtra("name", arrayList.get(selectedPosition).name);
            intent.putExtra("number", arrayList.get(selectedPosition).number);
            intent.putExtra("email", arrayList.get(selectedPosition).email);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class View_Holder extends RecyclerView.ViewHolder {
        TextView name, number, email;
        ImageView image;

        public View_Holder(@NonNull View itemView) {
            super(itemView);

            // get views in the item of the viewholder
            name = itemView.findViewById(R.id.contact_name);
            number = itemView.findViewById(R.id.contact_no);
            image = itemView.findViewById(R.id.image);
            email = itemView.findViewById(R.id.email);

        }
    }


}
