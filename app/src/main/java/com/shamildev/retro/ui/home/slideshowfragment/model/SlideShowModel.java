package com.shamildev.retro.ui.home.slideshowfragment.model;

import com.shamildev.retro.ui.common.model.BaseModel;



/**
 * Created by Shamil Lazar.
 */

public abstract class SlideShowModel extends BaseModel {



    public abstract Boolean checkUser();
    public abstract void initData();
    public abstract void initConfiguration();

}
