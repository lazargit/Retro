package com.shamildev.retro.ui.home.fragment.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.shamildev.retro.R;
import com.shamildev.retro.domain.models.Movie;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by Schamil Mischijew on 05.11.2017.
 */

public class SearchResultRecycleViewAdapter extends RecyclerView.Adapter<SearchResultRecycleViewAdapter.MyViewHolder> {

    private ArrayList<Movie> movieArrayList = new ArrayList<>();

    Glide glide;
    Context context;





    @Inject
    public SearchResultRecycleViewAdapter(ArrayList<Movie> movieArrayList, Glide glide, Context context) {
        this.movieArrayList = movieArrayList;
        this.glide = glide;
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_thumbnail,parent,false);
        return new MyViewHolder(view,this.glide);
    }

    public  ArrayList<Movie> getMovieArrayList(){
        return movieArrayList;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // movieArrayList.get(position).getPoster_path();


       // Log.d("onBindViewHolder","https://image.tmdb.org/t/p/original"+movieArrayList.get(position).posterPath());

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


        if(movieArrayList.get(position).posterPath()!= null){

            Glide.with(this.context).load("https://image.tmdb.org/t/p/w500/"+movieArrayList.get(position).posterPath())
                    .thumbnail(0.2f)

                    .apply(new RequestOptions()

                                    .diskCacheStrategy(DiskCacheStrategy.ALL)

//                            .placeholder(R.drawable.movie1)
//                            .dontAnimate()
//
//
//                            .override(250, 300) // set exact size
                            .fitCenter()
                     )
                    .transition(DrawableTransitionOptions.withCrossFade())






//            Glide.with(this.context)
//
//
//                    .load("https://image.tmdb.org/t/p/w500/"+movieArrayList.get(position).posterPath())
//
//                    .apply(new RequestOptions()
//                            .placeholder(R.drawable.movie1)
//                            .dontAnimate()
//
//
//                            .override(250, 300) // set exact size
//                            .fitCenter())



                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            Log.e("TAG","onLoadFailed.."+movieArrayList.get(position).title());
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                            Log.e("TAG","onResourceReady"+isFirstResource);
                            return false;
                        }
                    })



                    .into(holder.imageView);
        }else {
            Glide.with(this.context)
                    .load(R.drawable.movie1)
                    .into(holder.imageView);

        }



//      picasso.load("https://image.tmdb.org/t/p/w185"+movieArrayList.get(position).posterPath())
//                //.placeholder(R.drawable.movie1)
//                //.fit()
//
//                .resize(200,250)
//                .onlyScaleDown()
//               // .centerCrop()
//                .noFade()
//                .into(holder.imageView);

      //  holder.textViewTitle.setText(movieArrayList.get(position).title());
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }







    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }
    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private SearchResultRecycleViewAdapter.ClickListener clickListener;



        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final SearchResultRecycleViewAdapter.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }


        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;


        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }





    public static class MyViewHolder extends RecyclerView.ViewHolder{


        ImageView imageView;
        TextView textViewTitle;
        public MyViewHolder(View itemView,Glide glide) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.thumbnail);
          //  textViewTitle = (TextView) itemView.findViewById(R.id.userrating);
            //https://image.tmdb.org/t/p/original/ogrFPm9i2oVo6CiSXl0XNSPBzjI.jpg
        }
    }
}
