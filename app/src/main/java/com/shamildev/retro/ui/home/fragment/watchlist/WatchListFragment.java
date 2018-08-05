package com.shamildev.retro.ui.home.fragment.watchlist;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shamildev.retro.R;
import com.shamildev.retro.domain.MediaItem;
import com.shamildev.retro.helper.RecyclerItemTouchHelper;
import com.shamildev.retro.retroimage.core.RetroImage;
import com.shamildev.retro.ui.common.view.BaseViewFragmentV4;
import com.shamildev.retro.ui.home.fragment.adapter.RecyclerViewPagerAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by Shamil Lazar on 25.05.2018.
 */

public class WatchListFragment extends BaseViewFragmentV4<WatchListPresenter> implements WatchListView,RecyclerItemTouchHelper.RecyclerItemTouchHelperListener  {

    public static final String TAG = WatchListFragment.class.getSimpleName();
    private Unbinder butterKnifeUnbinder;
    private WatchListAdapter recyclerViewPagerAdapter;


    @Inject
    RetroImage retroImage;

//    @BindView(R.id.imgsliderview_header)
//    ImageSliderView imgsliderHeader;

    @BindView(R.id.recycler_view_watchlist)
    RecyclerView recyclerViewWatchList;


    CoordinatorLayout coordinatorLayout;

    public WatchListFragment() {
        Log.e("TAG","WatchListFragment");
        setRetainInstance(true);



    }


    public static WatchListFragment instance() {
        final WatchListFragment fragment = new WatchListFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_watchlist, container, false);

        coordinatorLayout = getActivity().findViewById(R.id.coordinator_layout);


        butterKnifeUnbinder = ButterKnife.bind(this, view);



        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.init(getContext(),this);

    }

    @Override
    public void fillWatchList(List<MediaItem> watchList) {
        setupRecyclerView(recyclerViewWatchList,watchList);
    }

    private void setupRecyclerView(RecyclerView recyclerView, List<MediaItem> watchList) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);




        recyclerViewPagerAdapter = new WatchListAdapter(watchList, getContext(), retroImage, this);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator()); //  recyclerView.setItemAnimator(new TranslateItemAnimator());

        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(recyclerViewPagerAdapter);


        recyclerView.addOnItemTouchListener(new RecyclerViewPagerAdapter.RecyclerTouchListener(getContext(),recyclerView,new RecyclerViewPagerAdapter.ViewHolderListener(){

            @Override
            public void onLoadCompleted(ImageView view, int adapterPosition) {


            }

            @Override
            public void onItemClicked(View view, int adapterPosition) {

                //SearchActivity activity = (SearchActivity) getActivity();
                //   HomePageFragment fragment = HomePageFragment.with(resultWrapper, "tag", adapterPosition);

                //WatchListFragment instance = WatchListFragment.instance();

                //homeActivity.loadFragment(instance);
//                SearchActivity homeActivity = new SearchActivity();
//                homeActivity.

                // final View fragmentView = inflater.inflate(R.layout.fragment_home, container, false);
                // addChildFragment(R.id.fragmentContainer,HomePageFragment.with(resultWrapper,"tag",adapterPosition));

//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .setReorderingAllowed(true) // Optimize for shared element transition
//                      //  .addSharedElement(transitioningView, transitioningView.getTransitionName())
//                        .replace(R.id.fragmentContainer,HomePageFragment.with(resultWrapper,"tag",adapterPosition), HomePageFragment.class
//                                .getSimpleName())
//                        .addToBackStack(HomePageFragment.TAG)
//                        .commit();



                // ((TransitionSet) getExitTransition()).excludeTarget(view, true);

                //  CustomImageView transitioningView = view.findViewById(R.id.imageViewRectItem);




                // presenter.onItemClick(adapterPosition,transitioningView,view);

            }
        }));


        // adding item touch helper
        // only ItemTouchHelper.LEFT added to detect Right to Left swipe
        // if you want both Right -> Left and Left -> Right
        // add pass ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT as param
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT,this );
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);





        //  baliAdapter = new MediaListAdapter(this, getActivity());
    }




    @Override
    public void makeToast(String message) {

    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {


        if (viewHolder instanceof WatchListAdapter.ViewHolder) {
            // get the removed item name to display it in snack bar
            String name = presenter.mediaItems().get(viewHolder.getAdapterPosition()).itemTitle();

            // backup of removed item for undo purpose
            final MediaItem deletedItem = presenter.mediaItems().get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            // remove the item from recycler view
            recyclerViewPagerAdapter.removeItem(viewHolder.getAdapterPosition());

            // showing snack bar with Undo option
            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, name + " removed from cart!", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // undo is selected, restore the deleted item
                    recyclerViewPagerAdapter.restoreItem(deletedItem, deletedIndex);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }

    }
}
