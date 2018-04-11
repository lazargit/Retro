package com.shamildev.retro.ui.home.fragment.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.shamildev.retro.ui.home.fragment.view.ImagePagerFragment;

/**
 * Created by Shamil Lazar on 18.03.2018.
 */

public class ImagePagerAdapter extends PagerAdapter {
    public ImagePagerAdapter(ImagePagerFragment imagePagerFragment) {
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}
