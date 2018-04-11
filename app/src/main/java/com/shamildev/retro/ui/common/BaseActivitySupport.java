package com.shamildev.retro.ui.common;


import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.shamildev.retro.navigation.Navigator;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */
public abstract class BaseActivitySupport extends AppCompatActivity implements  HasSupportFragmentInjector, AppBarLayout.OnOffsetChangedListener {

    @Inject
    protected Navigator navigator;

    /**
     * A reference to the FragmentManager is injected and used instead of the getter method. This
     * enables ease of mocking and verification in tests (in case Activity needs testing).
     *
     * For more details, see https://github.com/vestrel00/android-dagger-butterknife-mvp/pull/52
     */
    @Inject
    @Named(BaseActivityModule.ACTIVITY_FRAGMENT_MANAGER2)
    protected FragmentManager fragmentManager;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);
    }


    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentInjector;
    }



    protected  void addFragment(@IdRes int containerViewId, Fragment fragment) {
        Log.d("resplace",">>"+fragment.getClass().getSimpleName());
        fragmentManager.beginTransaction()
                .add(containerViewId,fragment)
                .commit();
    }


    protected  void addFragment(@IdRes int containerViewId, Fragment fragment, @AnimRes int enterAni, @AnimRes int exitAni) {
        Log.d("replace",">>"+fragment.getClass().getSimpleName());
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(enterAni,exitAni,enterAni,exitAni);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.add(containerViewId,fragment,fragment.getClass().getSimpleName()).commit();


    }

    protected  void removeFragment( String tag, @AnimRes int enterAni, @AnimRes int exitAni) {
        Log.d("remove",">>"+tag);
        Fragment fragmentByTag = fragmentManager.findFragmentByTag(tag);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(enterAni,exitAni,enterAni,exitAni);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.remove(fragmentByTag).commit();


    }
    protected Fragment getFragmentByTag(String tag) {
        Log.d("getFragmentByTag",">>"+tag);
        return fragmentManager.findFragmentByTag(tag);



    }

}
