package com.shamildev.retro.ui.home.fragment.view;

import com.shamildev.retro.ui.common.view.MVPView;
import com.shamildev.retro.ui.home.slideshowfragment.adapter.SlideShowPageAdapter;

/**
 * Created by Shamil Lazar on 13.05.2018.
 */

public interface ImageViewPagerView extends MVPView {

    void initViewPager(SlideShowPageAdapter myViewPagerAdapter, Integer pos);

}
