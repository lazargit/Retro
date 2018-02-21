package com.shamildev.retro.ui.home;


import android.support.v7.app.AppCompatActivity;

import com.shamildev.retro.di.scope.PerActivity;
import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.common.BaseActivityModule;
import com.shamildev.retro.ui.home.fragment.view.HomeFragment;
import com.shamildev.retro.ui.home.fragment.view.HomeFragmentModule;


import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**



 * Provides WatchList activity dependencies.
 */
@Module(includes = BaseActivityModule.class)
public abstract class HomeActivityModule {

    /**
     * Provides the injector for the {@link com.shamildev.retro.ui.home.fragment.view.HomeFragment}, which has access to the dependencies
     * provided by this activity and application instance (singleton scoped objects).
     */
    @PerFragment
    @ContributesAndroidInjector(modules = HomeFragmentModule.class)
    abstract HomeFragment homeFragmentInjector();

    /**
     * As per the contract specified in {@link BaseActivityModule}; "This must be included in all
     * activity modules, which must provide a concrete implementation of {@link AppCompatActivity}."
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
    abstract AppCompatActivity activity(HomeActivity activity);
}
