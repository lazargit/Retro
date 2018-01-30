package com.shamildev.retro.ui.watchlist;

import android.os.Bundle;

import com.shamildev.retro.R;
import com.shamildev.retro.ui.common.BaseActivity;
import com.shamildev.retro.ui.watchlist.fragment.view.WatchListFragment;

/**
 * Created by Schamil on 30.10.2017.
 */

public class WatchListActivity extends BaseActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlist);


        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, new WatchListFragment());
        }
    }



}
