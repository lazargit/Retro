package com.shamildev.retro.ui.home.slideshowfragment.adapter;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.shamildev.retro.R;
import com.shamildev.retro.domain.DomainObject;
import com.shamildev.retro.domain.MediaItem;
import com.shamildev.retro.retroimage.core.RetroImage;
import com.shamildev.retro.retroimage.core.RetroImageRequestListener;
import com.shamildev.retro.ui.home.fragment.presenter.ImageViewPagerPresenter;
import com.shamildev.retro.ui.home.fragment.presenter.ImageViewPagerPresenterImpl;
import com.shamildev.retro.ui.home.fragment.view.ImageViewPagerFragment;
import com.shamildev.retro.ui.home.slideshowfragment.presenter.SlideShowPresenterImpl;
import com.shamildev.retro.retroimage.views.RetroImageView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Shamil Lazar on 28.03.2018.
 */

public class SlideShowPageAdapter extends PagerAdapter {


    private final FragmentActivity activity;
    private  RetroImage retroImage;
    private  ImageViewPagerFragment imageViewPagerFragment;
    private  ImageViewPagerPresenter imageViewPagerPresenter;

    private SlideShowPresenterImpl slideShowPresenter;
    private  RequestManager requestManager;
    private  LayoutInflater layoutInflater;
    private ArrayList<DomainObject> items;
    private FloatingActionButton fabWatchlist;


    public SlideShowPageAdapter(FragmentActivity activity, ImageViewPagerFragment imageViewPagerFragment,List<DomainObject> mResults, ImageViewPagerPresenterImpl imageViewPagerPresenter, RetroImage retroImage) {

        this.activity = activity;
        this.items = (ArrayList<DomainObject>) mResults;
        this.requestManager = Glide.with(this.activity);
        this.imageViewPagerPresenter = imageViewPagerPresenter;
        this.retroImage =  retroImage;
        this.imageViewPagerFragment = imageViewPagerFragment;

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

        RetroImageView imageViewPreview = (RetroImageView) view.findViewById(R.id.imageViewRectItem);

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



        MediaItem mediaItem = (MediaItem) items.get(position);

      //  imageViewPreview.setTransitionName(mediaItem.itemTitle());

        if (mediaItem.itemPosterPath() != null) {
            loadImage(mediaItem, imageViewPreview);
        }
        if (mediaItem.itemTitle() != null) {
            txtViewTitle.setText(mediaItem.itemTitle());
        }
        if (mediaItem.itemIsInWatchList() != null){
            if (mediaItem.itemIsInWatchList()) {
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





    private void loadImage(MediaItem item, RetroImageView retroImageView){


       this.retroImage
                .load(item)
                .Poster()
                .w780()
                .into(retroImageView, new RetroImageRequestListener() {
                    @Override
                    public boolean onLoadFailed() {
                        imageViewPagerFragment.startPostponedEnterTransition();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady() {
                        imageViewPagerFragment.startPostponedEnterTransition();
                        return false;
                    }
                });


        // this.processImageHelper.downloadImageTo(viewHolder);

//        this.requestManager.
//                load("https://image.tmdb.org/t/p/w500/"+imagePath) //TODO dyn. image Path
//
//                .apply(new RequestOptions()
//
//                                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
//                                .dontAnimate()
//
//
////                            .placeholder(R.drawable.movie1)
////                            .dontAnimate()
////
////
//                              // .override(viewHolder.getWidth(), viewHolder.getHeight()) // set exact size
//                             .centerCrop()
//                )
//                .transition(DrawableTransitionOptions.withCrossFade())
//                .listener(new RequestListener<Drawable>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                        Log.e("TAG","onLoadFailed..");
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//
//                        Log.e("TAG","onResourceReady"+isFirstResource);
//                        return false;
//                    }
//                })
//                .into(viewHolder);



    }


}
