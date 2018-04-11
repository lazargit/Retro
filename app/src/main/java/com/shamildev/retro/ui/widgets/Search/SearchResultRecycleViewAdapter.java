package com.shamildev.retro.ui.widgets.Search;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
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
import com.shamildev.retro.domain.DomainObject;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.Person;
import com.shamildev.retro.domain.models.TVShow;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Schamil Mischijew on 05.11.2017.
 */

public class SearchResultRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<DomainObject> domainObjects;

    Glide glide;
    Context context;
    private boolean isLoadingAdded;

    private final int ITEM_MOVIE = 0, ITEM_SHOW = 1, ITEM_PERSON = 2, ITEM_LOADER = 5;


    @Inject
    public SearchResultRecycleViewAdapter(Glide glide, Context context) {
        domainObjects = new ArrayList<>();
        this.glide = glide;
        this.context = context;

    }





    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("SearchAdapter","viewType "+viewType);
     

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v2 = inflater.inflate(R.layout.loading_icon, parent, false);
        switch (viewType) {
            case ITEM_MOVIE:
                viewHolder = getViewHolder(parent, inflater);
                break;
            case ITEM_SHOW:
                viewHolder = getViewHolder(parent, inflater);
                break;
            case ITEM_PERSON:
                viewHolder = getViewHolderPerson(parent, inflater);
                break;
            case ITEM_LOADER:

                viewHolder = new LoadingVH(v2);
                break;
        }
        return viewHolder;
        
    }





    @NonNull
    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View v1 = inflater.inflate(R.layout.searchresult_row, parent, false);
        viewHolder = new MyViewHolder(v1);
        return viewHolder;
    }

    @NonNull
    private RecyclerView.ViewHolder getViewHolderPerson(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View v1 = inflater.inflate(R.layout.searchresult_person_row, parent, false);
        viewHolder = new MyViewHolderPerson(v1);
        return viewHolder;
    }


    public  List<DomainObject> getArray(){
        return domainObjects;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        String imagePath = null;
        String name = null;
        String nameOrig = null;

        Log.e("ADAPTER","ITEMPOS: "+getItemViewType(position));
        switch (getItemViewType(position)) {
            case ITEM_MOVIE:
                final MyViewHolder myViewHolder = (MyViewHolder) holder;
                if(domainObjects.get(position) instanceof Movie){
                    Movie movie = (Movie) domainObjects.get(position);
                    imagePath = movie.posterPath();
                    name = movie.title();
                    nameOrig = movie.originalTitle();
                }




                if(myViewHolder.textViewTitle!=null){
                    myViewHolder.textViewTitle.setText(name);
                    myViewHolder.textViewTitleOrig.setText(nameOrig);
                }

                if(imagePath!= null){
                    loadImage(imagePath,myViewHolder);
                }else{
                    Glide.with(this.context)
                            .load(R.drawable.movie1)
                            .into(myViewHolder.imageView);
                }


                break;
            case ITEM_SHOW:
                final MyViewHolder myViewHolderShow = (MyViewHolder) holder;


                if(domainObjects.get(position) instanceof TVShow){
                    TVShow tvShow = (TVShow) domainObjects.get(position);
                    imagePath = tvShow.posterPath();
                    name = tvShow.name();
                    nameOrig = tvShow.originalName();
                }



                if(myViewHolderShow.textViewTitle!=null){
                    myViewHolderShow.textViewTitle.setText(name);
                    myViewHolderShow.textViewTitleOrig.setText(nameOrig);
                }

                if(imagePath!= null){
                    loadImage(imagePath,myViewHolderShow);
                }else{
                    Glide.with(this.context)
                            .load(R.drawable.movie1)
                            .into(myViewHolderShow.imageView);
                }


                break;
            case ITEM_PERSON:
                final MyViewHolderPerson myViewHolderPerson = (MyViewHolderPerson) holder;
                String knownFor ="";
                if(domainObjects.get(position) instanceof Person){
                    Person person = (Person) domainObjects.get(position);
                    imagePath = person.profilePath();
                    name = person.name();
                    List<DomainObject> list = person.knownFor();
                    for (DomainObject item :list ) {
                             if(item instanceof Movie){
                                 Movie movie = (Movie) item;
                                 knownFor += movie.title()+"|";
                             }
                    }

                }
                if(myViewHolderPerson.textViewTitle!=null){
                    myViewHolderPerson.textViewTitle.setText(name);
                    myViewHolderPerson.textViewTitleOrig.setText(knownFor);
                }
                if(imagePath!= null){
                    loadProfileImage(imagePath,myViewHolderPerson);
                }else{
                    Glide.with(this.context)
                            .load(R.drawable.user_avatar)
                            .into(myViewHolderPerson.imageView);
                }
                break;

            case ITEM_LOADER:
//                Do nothing
                break;

        }







    }



    @Override
    public int getItemCount() {
        return domainObjects == null ? 0 : domainObjects.size();
    }



    @Override
    public int getItemViewType(int position) {

       // Log.e("Pagination","getItemViewType "+(domainObjects.size() - 1) +" <= "+ isLoadingAdded);
        return getItemType(position);
    }

    public int getItemType(int position) {
        if(domainObjects.get(position) instanceof Movie){
            return 0;
        }
        if(domainObjects.get(position) instanceof TVShow){
            return 1;
        }
        if(domainObjects.get(position) instanceof Person){
            return 2;
        }

        return 5;

    }


    private void loadProfileImage(String imagePath, MyViewHolderPerson myViewHolder){
        Log.e("ADAPTER","loadProfileImage: "+imagePath);

        Glide.with(this.context)
                .load("https://image.tmdb.org/t/p/w92/"+imagePath)
                .into(myViewHolder.imageView);



    }

    private void loadImage(String imagePath, MyViewHolder myViewHolder){

        Glide.with(this.context).load("https://image.tmdb.org/t/p/w92/"+imagePath) //TODO dyn. image Path


                .apply(new RequestOptions()

                                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                                .dontAnimate()

//                            .placeholder(R.drawable.movie1)
//                            .dontAnimate()
//
//
                               // .override(50, 50) // set exact size
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
                .into(myViewHolder.imageView);



    }





    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }


    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private SearchResultRecycleViewAdapter.ClickListener clickListener;



        public RecyclerTouchListener(SearchResultWidget context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context.getContext(), new GestureDetector.SimpleOnGestureListener() {
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




    /*
    * Helpers
    * ______________________________________________________________________________________________
    */

    public void add(DomainObject r) {
        domainObjects.add(r);
        notifyItemInserted(this.domainObjects.size() - 1);
    }

    public void addAll(List<DomainObject> domainObjectList) {
        for (DomainObject result : domainObjectList) {
            add(result);
        }
    }

    public void remove(DomainObject r) {
        int position = this.domainObjects.indexOf(r);
        if (position > -1) {
            this.domainObjects.remove(position);
            notifyItemRemoved(position);

        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }


    public void addLoadingFooter() {
         isLoadingAdded = true;
         Movie build = Movie.builder()
                .id(0L)
                .title("")
                .build();
         add(build);
    }

    public void removeLoadingFooter() {

            isLoadingAdded = false;
            int position = this.domainObjects.size() - 1;
            DomainObject result = getItem(position);

            if (result != null) {
                this.domainObjects.remove(position);
                notifyItemRemoved(position);
            }

    }

    public DomainObject getItem(int position) {
        return  this.domainObjects.get(position);
    }



    /*

    ViewHolder
     */

    protected class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        ImageView imageView;
        TextView textViewTitle;
        TextView textViewTitleOrig;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageView = (ImageView) itemView.findViewById(R.id.searchresult_row_image);
            textViewTitle = (TextView) itemView.findViewById(R.id.searchresult_row_name);
            textViewTitleOrig = (TextView) itemView.findViewById(R.id.searchresult_row_nameOrig);

        }

        @Override
        public void onClick(View v) {

        }
    }


    protected class MyViewHolderPerson extends RecyclerView.ViewHolder implements View.OnClickListener{


        ImageView imageView;
        TextView textViewTitle;
        TextView textViewTitleOrig;
        public MyViewHolderPerson(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageView = (ImageView) itemView.findViewById(R.id.profile_image);
            textViewTitle = (TextView) itemView.findViewById(R.id.searchresult_row_name);
            textViewTitleOrig = (TextView) itemView.findViewById(R.id.searchresult_row_known);

        }

        @Override
        public void onClick(View v) {

        }
    }


    protected class LoadingVH extends RecyclerView.ViewHolder {
        public LoadingVH(View view) {
            super(view);
        }
    }
}
