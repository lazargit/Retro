package com.shamildev.retro.ui.home.fragment.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shamildev.retro.R;
import com.shamildev.retro.ui.home.fragment.adapter.ImagePagerAdapter;

import io.reactivex.annotations.Nullable;

/**
 * Created by Shamil Lazar on 18.03.2018.
 */

public class ImagePagerFragment extends Fragment {

    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewPager = (ViewPager) inflater.inflate(R.layout.fragment_imagepager, container, false);
        viewPager.setAdapter(new ImagePagerAdapter(this));
        // Set the current position and add a listener that will update the selection coordinator when
        // paging the images.
        //   viewPager.setCurrentItem(MainActivity.currentPosition);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                //  MainActivity.currentPosition = position;
            }
        });
        return viewPager;
    }

}
