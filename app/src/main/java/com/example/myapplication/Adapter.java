package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>
{
    private final static String URL = "http://cinema.areas.su/up/images/";
    private List<Body> mkinos;
    private Context context;
    public Adapter(List<Body> bodies)
    {
        this.mkinos = bodies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Body body = mkinos.get(position);
        holder.textView.setText(body.getName());
        Picasso.with(context).load(URL + body.getPoster()).resize(200, 200).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if(mkinos == null) {
            return 0;
        }
        return mkinos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView textView;
        ImageView imageView;
        ViewHolder(View itemView)
        {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
