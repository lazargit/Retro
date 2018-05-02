package com.shamildev.retro.ui.home.slideshowfragment.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
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
import com.shamildev.retro.domain.MediaItem;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.TVShow;
import com.shamildev.retro.ui.home.fragment.adapter.RecyclerViewPagerAdapter;
import com.shamildev.retro.ui.home.slideshowfragment.presenter.SlideShowPresenterImpl;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Shamil Lazar on 28.03.2018.
 */

public class SlideShowPageAdapter extends PagerAdapter {


    private final FragmentActivity activity;
    private SlideShowPresenterImpl slideShowPresenter;
    private  RequestManager requestManager;
    private  LayoutInflater layoutInflater;
    private ArrayList<DomainObject> items;
    private FloatingActionButton fabWatchlist;

    public SlideShowPageAdapter(FragmentActivity activity, List<DomainObject> mResults, SlideShowPresenterImpl slideShowPresenter) {
        this.activity = activity;
        this.items = (ArrayList<DomainObject>) mResults;
        this.requestManager = Glide.with( this.activity);
        this.slideShowPresenter = slideShowPresenter;

    }

    public SlideShowPageAdapter(FragmentActivity activity, List<DomainObject> results) {
        this.activity = activity;
        this.items = (ArrayList<DomainObject>) results;
        this.requestManager = Glide.with( this.activity);

    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // return super.instantiateItem(container, position);

        layoutInflater = (LayoutInflater) this.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.image_fullscreen_preview, container, false);

        ImageView imageViewPreview = (ImageView) view.findViewById(R.id.image_preview);
        TextView txtViewTitle = (TextView) view.findViewById(R.id.txtView_title);
        fabWatchlist = (FloatingActionButton) view.findViewById(R.id.fab_watchlist);
        fabWatchlist.setOnClickListener(v -> {
            Log.e("FAB ", ">" + position);
            MediaItem mediaItem = (MediaItem) this.items.get(position);
                      mediaItem.itemAddToWatchList();
                     // this.items.get(position) = mediaItem;
            this.slideShowPresenter.addItemToWatchList(mediaItem);
        });

        imageViewPreview.setOnClickListener(v -> {
            Log.e("SlideShowPageAdapter", ">" + position);
        });


        String imagePath = null;
        String title = null;
        Boolean isInWatchList = false;
        Boolean test = true;
        if (items.get(position) instanceof Movie) {
            Movie movie = (Movie) items.get(position);
            imagePath = movie.posterPath();
            title = movie.title();
            isInWatchList = movie.itemIsInWatchList();
            Log.e("instantiateItem", ">" + movie.itemIsInWatchList());
            //popularity = movie.popularity();
        }
        if (items.get(position) instanceof TVShow) {
            TVShow tvShow = (TVShow) items.get(position);
            imagePath = tvShow.posterPath();
            title = tvShow.name();
            isInWatchList = tvShow.itemIsInWatchList();
            // popularity = tvShow.popularity();

        }

        if (imagePath != null) {
            loadImage(imagePath, imageViewPreview);
        }
        if (title != null) {
            txtViewTitle.setText(title);
        }
        if (isInWatchList != null){
            if (isInWatchList) {
                fabWatchlist.setImageResource(R.drawable.ic_bookmark_black_24dp);
            }
        }





       container.addView(view);

        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == ((View) obj);
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }





    private void loadImage(String imagePath, ImageView viewHolder){

        this.requestManager.
                load("https://image.tmdb.org/t/p/w500/"+imagePath) //TODO dyn. image Path

                .apply(new RequestOptions()

                                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                                .dontAnimate()


//                            .placeholder(R.drawable.movie1)
//                            .dontAnimate()
//
//
                              // .override(viewHolder.getWidth(), viewHolder.getHeight()) // set exact size
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
                .into(viewHolder);



    }


}
