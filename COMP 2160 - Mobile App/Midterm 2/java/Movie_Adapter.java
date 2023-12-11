package com.example.movies;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class Movie_Adapter extends RecyclerView.Adapter<Movie_Adapter.View_Holder> {
    Context context;
    ArrayList<Movie_modal> arrayList;
    ArrayList<Movie_modal> selectedMovies = new ArrayList<>();
    int selectedCount = 0;

    Movie_Adapter(Context context, ArrayList<Movie_modal> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the view
        View view = LayoutInflater.from(context).inflate(R.layout.movie_row, parent, false);
        View_Holder viewHolder = new View_Holder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder holder, int position) {
        // Bind the data
        holder.image.setImageResource(arrayList.get(position).img);
        holder.name.setText(arrayList.get(position).name);
        holder.releaseDate.setText(arrayList.get(position).releaseDate);
        Movie_modal movie = arrayList.get(position);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class View_Holder extends RecyclerView.ViewHolder {
        TextView name, releaseDate;
        ImageView image;

        public View_Holder(@NonNull View itemView) {
            super(itemView);

            // get views in the item of the viewholder
            name = itemView.findViewById(R.id.tv_movie_name);
            releaseDate = itemView.findViewById(R.id.tv_release_date);
            image = itemView.findViewById(R.id.image);

            itemView.setOnLongClickListener(this::onLongClick);


        }

        public boolean onLongClick(View v) {
            int position = getAdapterPosition();
            Movie_modal movie = arrayList.get(position);

            if (selectedMovies.contains(movie)) {
                // Item is already selected, so deselect it and remove from list
                selectedMovies.remove(movie);
                movie.setSelected(false);
                itemView.setBackgroundColor(Color.BLUE);
                Toast.makeText(context, "Selected movies: " + getSelectedMovies(), Toast.LENGTH_SHORT).show();
            } else if (selectedMovies.size() < 2) {
                // Item is not selected, so select it and add to list
                selectedMovies.add(movie);
                movie.setSelected(true);
                itemView.setBackgroundColor(Color.RED);
                Toast.makeText(context, "Selected movies: " + getSelectedMovies(), Toast.LENGTH_SHORT).show();
            } else {
                // Cannot select more than 2 movies
                Toast.makeText(context, "You cannot select more than 2 movies", Toast.LENGTH_SHORT).show();
            }

            return true;
        }

    }

    private String getSelectedMovies() {
        StringBuilder builder = new StringBuilder();
        for (Movie_modal movie : selectedMovies) {
            builder.append(movie.getName());
            builder.append(", ");
        }
        // Remove trailing comma and space
        if (builder.length() > 0) {
            builder.setLength(builder.length() - 2);
        }
        return builder.toString();
    }


}
