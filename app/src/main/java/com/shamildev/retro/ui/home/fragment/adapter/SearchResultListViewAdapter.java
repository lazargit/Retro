package com.shamildev.retro.ui.home.fragment.adapter;

import android.app.Activity;
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
import android.widget.ArrayAdapter;
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

import javax.inject.Inject;

/**
 * Created by Schamil Mischijew on 05.11.2017.
 */

public class SearchResultListViewAdapter extends ArrayAdapter<DomainObject> {

    private ArrayList<DomainObject> domainObjects;

    Glide glide;
    Context context;

    @Inject
    public SearchResultListViewAdapter(@NonNull Context context, int resource, ArrayList<DomainObject> domainObjects,@NonNull Glide glide) {
        super(context, resource);
        this.domainObjects = domainObjects;
        this.glide = glide;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);

    }

    //Since we have two types of items here, we'll return 2.
    @Override
    public int getViewTypeCount() {
        return 2;
    }





}
