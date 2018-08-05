package com.shamildev.retro.ui.mylist.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.shamildev.retro.R;
import com.shamildev.retro.domain.MediaItem;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.TVShow;
import com.shamildev.retro.retroimage.core.RetroImage;
import com.shamildev.retro.retroimage.core.RetroImageRequestListener;
import com.shamildev.retro.ui.mylist.fragment.view.MyListFragment;
import com.shamildev.retro.retroimage.views.RetroImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * Created by Shamil Lazar on 14.03.2018.
 */

public class MyListAdapter extends RecyclerView.Adapter {


    private final Fragment fragment;
    private final RetroImage retroImage;


    /**
     * A listener that is attached to all ViewHolders to handle image loading events and clicks.
     */
    public interface ViewHolderListener {

        void onLoadCompleted(ImageView view, int adapterPosition);

        void onItemClicked(View view, int adapterPosition);
    }



    private  ViewHolderListener viewHolderListener;
    ArrayList<MediaItem> movieItems = new ArrayList<>();
    Context context;
    private final int ITEM_MOVIE = 0, ITEM_SHOW = 1, ITEM_PERSON = 2, ITEM_LOADER = 5;




    public MyListAdapter(List<MediaItem> watchList, Context context, RetroImage retroImage, MyListFragment fragment) {

        this.fragment = fragment;

        this.movieItems = (ArrayList<MediaItem>) watchList;
        this.context = context;
        this.retroImage = retroImage;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);



        View row = inflater.inflate(R.layout.watchlist_item_row, parent, false);
        return new ViewHolder(row);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.onBind();
    }

    @Override
    public int getItemCount() {
        return movieItems.size();
    }
    public void removeItem(int position) {
        this.movieItems.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

    public void restoreItem(MediaItem item, int position) {
        this.movieItems.add(position, item);
        // notify item added by position
        notifyItemInserted(position);
    }


    private void loadImage(MediaItem item, ViewHolder viewHolder){




        this.retroImage
                    .load(item).Poster().w780()
                .into(viewHolder.imageView, new RetroImageRequestListener() {
                    @Override
                    public boolean onLoadFailed() {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady() {
                        fragment.startPostponedEnterTransition();
                        return false;
                    }
                });







    }


     public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


       private final AtomicBoolean enterTransitionStarted;
       private RetroImageView imageView;
       public RelativeLayout viewBackground, viewForeground;




        public ViewHolder(View itemView) {


            super(itemView);

            imageView =      itemView.findViewById(R.id.imageViewRectItem);
            viewBackground = itemView.findViewById(R.id.view_background);
            viewForeground = itemView.findViewById(R.id.view_foreground);


         //   View viewById = itemView.findViewById(R.id.square_view);
          //  viewById.setOnClickListener(this);
            this.enterTransitionStarted = new AtomicBoolean();



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
                    loadImage((MediaItem) movieItems.get(adapterPosition), this);
                  //  vote.setText(popularity.toString());

                }else{
//                    requestManager
//                            .load(R.drawable.movie1)
//                            .into(imageView);
                }


         //   imageView.setTransitionName(nameTitle);

        }

        @Override
        public void onClick(View v) {
            Log.e("onClick","#"+getAdapterPosition());
         //  viewHolderListener.onItemClicked(v, getAdapterPosition());

        }
    }





    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private MyListAdapter.ViewHolderListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final MyListAdapter.ViewHolderListener clickListener) {
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
