package com.shamildev.retro.ui.home.fragment.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.shamildev.retro.ui.home.fragment.view.ImageFragment;
import com.shamildev.retro.ui.home.fragment.view.ImagePagerFragment;


import static com.shamildev.retro.ui.home.fragment.adapter.ImageData.IMAGE_DRAWABLES;

/**
 * Created by Shamil Lazar on 18.03.2018.
 */

public class ImagePagerAdapter extends FragmentStatePagerAdapter {
    public ImagePagerAdapter(Fragment fragment) {
        super(fragment.getChildFragmentManager());
    }

    @Override
    public int getCount() {
        return IMAGE_DRAWABLES.length;
    }

    @Override
    public Fragment getItem(int position) {
        return ImageFragment.newInstance(IMAGE_DRAWABLES[position]);
    }
}
