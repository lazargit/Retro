package com.shamildev.retro.ui.watchlist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.shamildev.retro.R;
import com.shamildev.retro.domain.models.Movie;


import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by Schamil Mischijew on 05.11.2017.
 */

public class WatchListRecycleViewAdapter extends RecyclerView.Adapter<WatchListRecycleViewAdapter.MyViewHolder> {

    ArrayList<Movie> movieArrayList = new ArrayList<>();

    Glide glide;
    Context context;




    @Inject
    public WatchListRecycleViewAdapter(ArrayList<Movie> movieArrayList, Glide glide, Context context) {
        this.movieArrayList = movieArrayList;
        this.glide = glide;
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_movie,parent,false);
        return new MyViewHolder(view,this.glide);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // movieArrayList.get(position).getPoster_path();


        Log.d("onBindViewHolder","https://image.tmdb.org/t/p/original"+movieArrayList.get(position).posterPath());

        /*  Glide.with(context)

              //  .diskCacheStrategy(DiskCacheStrategy.ALL)
                .load("https://image.tmdb.org/t/p/w185"+movieArrayList.get(position).getPoster_path())
                .apply(new RequestOptions()
                            // .placeholder(R.drawable.logo2)
                             .override(200 ,250) // set exact size
                             .fitCenter() // keep memory usage low by fitting into (w x h) [optional]
                        )

                .into(holder.imageView);
        //GlideApp.with(this).load(photo).apply(fitCenterTransform()).into(fullscreenView);
*/



        Glide.with(holder.imageView)


                .load("https://image.tmdb.org/t/p/w185"+movieArrayList.get(position).posterPath())

                    .apply(new RequestOptions()
                    .placeholder(android.R.drawable.ic_dialog_alert)
                    .override(200, 250) // set exact size
                    .fitCenter())

                .into(holder.imageView);

//      picasso.load("https://image.tmdb.org/t/p/w185"+movieArrayList.get(position).posterPath())
//                //.placeholder(R.drawable.movie1)
//                //.fit()
//
//                .resize(200,250)
//                .onlyScaleDown()
//               // .centerCrop()
//                .noFade()
//                .into(holder.imageView);

        holder.textViewTitle.setText(movieArrayList.get(position).title());
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{


        ImageView imageView;
        TextView textViewTitle;
        public MyViewHolder(View itemView,Glide glide) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.movieImg);
            textViewTitle = (TextView) itemView.findViewById(R.id.titleTextView);
            //https://image.tmdb.org/t/p/original/ogrFPm9i2oVo6CiSXl0XNSPBzjI.jpg
        }
    }
}
