package com.shamildev.retro.ui.details.fragment.model;

import com.shamildev.retro.ui.common.model.BaseModel;



/**
 * Created by Shamil Lazar.
 */

public abstract class DetailsModel extends BaseModel{


        public abstract Boolean checkUser();
        public abstract void initData();
        public abstract void initConfiguration();
}
