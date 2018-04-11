package com.shamildev.retro.ui.home.fragment.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.transition.TransitionSet;
import android.support.v4.app.Fragment;
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
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.shamildev.retro.R;
import com.shamildev.retro.domain.DomainObject;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.TVShow;
import com.shamildev.retro.ui.home.fragment.view.ImagePagerFragment;
import com.shamildev.retro.ui.widgets.Search.SearchResultRecycleViewAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;


/**
 * Created by Shamil Lazar on 14.03.2018.
 */

public class RecyclerViewPagerAdapter extends RecyclerView.Adapter {


    /**
     * A listener that is attached to all ViewHolders to handle image loading events and clicks.
     */
    public interface ViewHolderListener {

        void onLoadCompleted(ImageView view, int adapterPosition);

        void onItemClicked(View view, int adapterPosition);
    }


    private final RequestManager requestManager;
    private  ViewHolderListener viewHolderListener;
    ArrayList<DomainObject> movieItems = new ArrayList<>();
    Context context;
    private final int ITEM_MOVIE = 0, ITEM_SHOW = 1, ITEM_PERSON = 2, ITEM_LOADER = 5;


    public RecyclerViewPagerAdapter(ArrayList<DomainObject> items, Context context) {


        this.movieItems = items;
        this.context = context;
        this.requestManager = Glide.with(context);


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);



        View row = inflater.inflate(R.layout.rect_item_row, parent, false);
        return new WideItemHolder(row,requestManager);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final WideItemHolder viewHolder = (WideItemHolder) holder;
        viewHolder.onBind();
    }

    @Override
    public int getItemCount() {
        return movieItems.size();
    }


    private void loadImage(String imagePath, WideItemHolder viewHolder){

        this.requestManager.
                load("https://image.tmdb.org/t/p/w500/"+imagePath) //TODO dyn. image Path

                .apply(new RequestOptions()

                                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                                .dontAnimate()


//                            .placeholder(R.drawable.movie1)
//                            .dontAnimate()
//
//
                                .override(viewHolder.itemView.getWidth(), viewHolder.itemView.getHeight()) // set exact size
                                .centerCrop()
                )
                .transition(DrawableTransitionOptions.withCrossFade())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Log.e("TAG","onLoadFailed..");
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                        Log.e("TAG","onResourceReady"+isFirstResource);
                        return false;
                    }
                })
                .into(viewHolder.imageView);



    }


    protected class WideItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        ImageView imageView;
        TextView vote;


        public WideItemHolder(View itemView, RequestManager requestManager) {


            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.imageViewRectItem);
            vote = (TextView) itemView.findViewById(R.id.vote);

            View viewById = itemView.findViewById(R.id.square_view);
            viewById.setOnClickListener(this);



        }

        void onBind() {
            int adapterPosition = getAdapterPosition();
           // setImage(adapterPosition);
            // Set the string value of the image resource as the unique transition name for the view.


            String imagePath = null;
            Float popularity = null;
            String nameTitle = null;
            String nameDes = null;

                if(movieItems.get(adapterPosition) instanceof Movie){
                    Movie movie = (Movie) movieItems.get(adapterPosition);
                    imagePath = movie.posterPath();
                    popularity = movie.popularity();
                }
                if(movieItems.get(adapterPosition) instanceof TVShow){
                    TVShow tvShow = (TVShow) movieItems.get(adapterPosition);
                    imagePath = tvShow.posterPath();
                    popularity = tvShow.popularity();

                }






                if(imagePath!=null) {
                    loadImage(imagePath, this);
                    vote.setText(popularity.toString());

                }else{
                    requestManager
                            .load(R.drawable.movie1)
                            .into(imageView);
                }


            imageView.setTransitionName(imagePath);

        }

        @Override
        public void onClick(View v) {
            Log.e("onClick","#"+getAdapterPosition());
           // viewHolderListener.onItemClicked(v, getAdapterPosition());

        }
    }



    private class ViewHolderListenerImpl {


        private final AtomicBoolean enterTransitionStarted;

        public ViewHolderListenerImpl() {

            this.enterTransitionStarted = new AtomicBoolean();

        }

    }



    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private RecyclerViewPagerAdapter.ViewHolderListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final RecyclerViewPagerAdapter.ViewHolderListener clickListener) {
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
                      clickListener.onItemClicked(child, recyclerView.getChildAdapterPosition(child));

                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onItemClicked(child, rv.getChildAdapterPosition(child));
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



}
