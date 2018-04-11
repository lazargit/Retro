package com.shamildev.retro.ui.home.slideshowfragment.view;

import com.shamildev.retro.ui.common.view.MVPView;
import com.shamildev.retro.ui.home.slideshowfragment.adapter.SlideShowPageAdapter;

/**
 * Created by Shamil Lazar on 23.03.2018.
 */

public interface SlideShowView extends MVPView {


    void initViewPager(SlideShowPageAdapter myViewPagerAdapter, Integer pos);


}
