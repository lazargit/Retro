package com.shamildev.retro.ui.mylist;


import android.support.v7.app.AppCompatActivity;

import com.shamildev.retro.di.scope.PerActivity;
import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.common.BaseActivityModule;
import com.shamildev.retro.ui.mylist.MyListActivity;
import com.shamildev.retro.ui.mylist.fragment.view.MyListFragment;
import com.shamildev.retro.ui.mylist.fragment.view.MyListFragmentModule;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**



 * Provides WatchList activity dependencies.
 */
@Module(includes = {BaseActivityModule.class})
public abstract class MyListActivityModule {


    @PerFragment
    @ContributesAndroidInjector(modules = MyListFragmentModule.class)
    abstract MyListFragment mylistFragmentInjector();




    @Binds
    @PerActivity
    abstract AppCompatActivity activity(MyListActivity activity);

}
