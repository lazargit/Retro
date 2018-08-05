package com.shamildev.retro.ui.search;


import android.support.v7.app.AppCompatActivity;

import com.shamildev.retro.di.scope.PerActivity;
import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.common.BaseActivityModule;
import com.shamildev.retro.ui.mylist.fragment.view.MyListFragment;
import com.shamildev.retro.ui.mylist.fragment.view.MyListFragmentModule;
import com.shamildev.retro.ui.search.fragment.view.SearchFragment;
import com.shamildev.retro.ui.search.fragment.view.SearchFragmentModule;
import com.shamildev.retro.ui.widgets.Search.view.SearchResultFragment;
import com.shamildev.retro.ui.widgets.Search.view.SearchResultFragmentModule;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**



 * Provides WatchList activity dependencies.
 */
@Module(includes = {BaseActivityModule.class})
public abstract class SearchActivityModule {


    @PerFragment
    @ContributesAndroidInjector(modules = SearchFragmentModule.class)
    abstract SearchFragment fragmentInjector();

    @PerFragment
    @ContributesAndroidInjector(modules = SearchResultFragmentModule.class)
    abstract SearchResultFragment searchResultFragmentInjector();




    @Binds
    @PerActivity
    abstract AppCompatActivity activity(SearchActivity activity);

}
