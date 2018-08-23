package com.shamildev.retro.ui.common.model;

import com.shamildev.retro.ui.common.presenter.Presenter;


/**
 * Created by Shamil Lazar.
 */
@SuppressWarnings("unchecked")
public abstract class BaseModel<T> {


     protected T presenter;

     public void setPresenter(Presenter presenter){
          this.presenter = (T)presenter;
     }


     public T getPresenter() {

          return ((T) presenter);
     }

}
