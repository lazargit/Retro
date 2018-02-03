package com.shamildev.retro.ui.details;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import com.shamildev.retro.di.scope.PerActivity;
import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.common.BaseActivityModule;
import com.shamildev.retro.ui.details.fragment.view.DetailsFragment;
import com.shamildev.retro.ui.details.fragment.view.DetailsFragmentModule;

import com.shamildev.retro.ui.watchlist.fragment.view.WatchListFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Shamil Lazar on 01.02.2018.


 * Provides WatchList activity dependencies.
 */
@Module(includes = BaseActivityModule.class)
public abstract class DetailsActivityModule {

    /**
     * Provides the injector for the {@link WatchListFragment}, which has access to the dependencies
     * provided by this activity and application instance (singleton scoped objects).
     */
    @PerFragment
    abstract DetailsFragment detailsFragmentInjector();

    /**
     * As per the contract specified in {@link BaseActivityModule}; "This must be included in all
     * activity modules, which must provide a concrete implementation of {@link Activity}."
     * <p>
     * This provides the activity required to inject the
     * {@link BaseActivityModule#ACTIVITY_FRAGMENT_MANAGER} into the
     * {@link com.shamildev.retro.ui.common.BaseActivity}.
     *
     * @param activity the WatchListActivity
     * @return the activity
     */
    @Binds
    @PerActivity
    abstract AppCompatActivity activity(DetailsActivity activity);
}
