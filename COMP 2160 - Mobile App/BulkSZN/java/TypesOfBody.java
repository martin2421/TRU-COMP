package com.example.bulkszn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class TypesOfBody extends AppCompatActivity {

    private ListView listView;
    private MyListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_types_of_body);

        listView = findViewById(R.id.listview);
        adapter = new MyListAdapter();
        listView.setAdapter(adapter);

        adapter.addItem(R.drawable.cut, "Cut", getString(R.string.cut));
        adapter.addItem(R.drawable.regular, "Regular", getString(R.string.regular));
        adapter.addItem(R.drawable.clean, "Clean", getString(R.string.clean));
        adapter.addItem(R.drawable.dirty, "Dirty", getString(R.string.dirty));

    }

    private class MyListAdapter extends BaseAdapter {

        private ArrayList<MyListItem> items;

        public MyListAdapter() {
            items = new ArrayList<>();
        }

        public void addItem(int imageResource, String title, String description) {
            MyListItem item = new MyListItem(imageResource, title, description);
            items.add(item);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            ViewHolder holder;

            if (view == null) {
                LayoutInflater inflater = getLayoutInflater();
                view = inflater.inflate(R.layout.list_item, parent, false);

                holder = new ViewHolder();
                holder.imageView = view.findViewById(R.id.image_body_type);
                holder.titleView = view.findViewById(R.id.tv_title_body_type);
                holder.descriptionView = view.findViewById(R.id.tv_description_body_type);

                view.setTag(holder);

            } else {
                holder = (ViewHolder) view.getTag();
            }

            MyListItem item = items.get(position);

            holder.imageView.setImageResource(item.imageResource);
            holder.titleView.setText(item.title);
            holder.descriptionView.setText(item.description);

            return view;
        }

        private class ViewHolder {
            ImageView imageView;
            TextView titleView;
            TextView descriptionView;
        }

        private class MyListItem {
            int imageResource;
            String title;
            String description;

            public MyListItem(int imageResource, String title, String description) {
                this.imageResource = imageResource;
                this.title = title;
                this.description = description;
            }
        }
    }
}
