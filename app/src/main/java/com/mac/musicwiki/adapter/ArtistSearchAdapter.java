package com.mac.musicwiki.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.mac.musicwiki.R;
import com.mac.musicwiki.album.view.AlbumActivity;
import com.mac.musicwiki.search.model.Datum;

import java.util.List;

/**
 * Created by SamMejia on 29/11/16.
 */

public class ArtistSearchAdapter extends RecyclerView.Adapter<ArtistSearchAdapter.MyViewHolder> {

    private List<Datum> artistAllList;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Datum item);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, album;
        public ImageView cover;


        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            album = (TextView) view.findViewById(R.id.album);
            cover = (ImageView) view.findViewById(R.id.cover);

        }

        public void bind(final Datum item, final OnItemClickListener listener) {
            name.setText(item.getArtist().getName().trim());
            album.setText(item.getAlbum().getTitle().trim());
            Glide.with(itemView.getContext()).load(item.getArtist().getPictureMedium()).into(cover);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }

    }


    public ArtistSearchAdapter(List<Datum> artistAllList, OnItemClickListener listener) {
        this.artistAllList = artistAllList;
        this.listener = listener;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_artist_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bind(artistAllList.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return artistAllList.size();
    }
}
