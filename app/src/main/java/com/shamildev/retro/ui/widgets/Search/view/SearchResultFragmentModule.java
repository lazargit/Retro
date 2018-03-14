package com.shamildev.retro.ui.widgets.Search.view;



import android.support.v4.app.Fragment;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.common.view.BaseFragmentModule;
import com.shamildev.retro.ui.home.fragment.presenter.HomePresenterModule;
import com.shamildev.retro.ui.widgets.Search.presenter.SearchResultPresenterModule;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;


/**
 * Created by Shamil Lazar on 13.12.2017.

 * Provides Splashfragment dependencies.
 */
@Module(includes = {
        BaseFragmentModule.class,
        SearchResultPresenterModule.class
})
public abstract class SearchResultFragmentModule {

    /**
     * As per the contract specified in {@link BaseFragmentModule}; "This must be included in all
     * fragment modules, which must provide a concrete implementation of {@link Fragment}
     * and named {@link BaseFragmentModule#FRAGMENT}.
     *
     * @param fragment the {@link SearchResultFragment}
     * @return the fragment
     */
    @Binds
    @Named(BaseFragmentModule.FRAGMENT_V4)
    @PerFragment
    abstract Fragment fragment(SearchResultFragment fragment);

    @Binds
    @PerFragment
    abstract SearchResultView watchListView(SearchResultFragment fragment);
}