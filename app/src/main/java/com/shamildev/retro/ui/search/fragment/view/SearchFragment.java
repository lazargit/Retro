package com.shamildev.retro.ui.search.fragment.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shamildev.retro.R;
import com.shamildev.retro.domain.MediaItem;

import com.shamildev.retro.retroimage.core.RetroImage;
import com.shamildev.retro.ui.common.view.BaseViewFragmentV4;
import com.shamildev.retro.ui.search.fragment.presenter.SearchPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by Shamil Lazar on 25.05.2018.
 */

public class SearchFragment extends BaseViewFragmentV4<SearchPresenter> implements SearchView  {

    public static final String TAG = SearchFragment.class.getSimpleName();



    @Inject
    RetroImage retroImage;
    private Unbinder butterKnifeUnbinder;


    public SearchFragment() {
        Log.e("TAG","WatchListFragment");
        setRetainInstance(true);



    }


    public static SearchFragment instance() {
        final SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);


        butterKnifeUnbinder = ButterKnife.bind(this, view);



        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.init(getContext());
        presenter.getDiscover();

    }

    @Override
    public void fillList(List<MediaItem> watchList) {

    }

    private void setupRecyclerView(RecyclerView recyclerView, List<MediaItem> watchList) {
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//
//
//
//
//        recyclerViewPagerAdapter = new MyListAdapter(watchList, getContext(), processImageHelper, this);
//
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setNestedScrollingEnabled(false);
//        recyclerView.setItemAnimator(new DefaultItemAnimator()); //  recyclerView.setItemAnimator(new TranslateItemAnimator());
//
//        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
//        recyclerView.setAdapter(recyclerViewPagerAdapter);
//
//
//        recyclerView.addOnItemTouchListener(new RecyclerViewPagerAdapter.RecyclerTouchListener(getContext(),recyclerView,new RecyclerViewPagerAdapter.ViewHolderListener(){
//
//            @Override
//            public void onLoadCompleted(ImageView view, int adapterPosition) {
//
//
//            }
//
//            @Override
//            public void onItemClicked(View view, int adapterPosition) {
//
//                //SearchActivity activity = (SearchActivity) getActivity();
//                //   HomePageFragment fragment = HomePageFragment.with(resultWrapper, "tag", adapterPosition);
//
//                //WatchListFragment instance = WatchListFragment.instance();
//
//                //homeActivity.loadFragment(instance);
////                SearchActivity homeActivity = new SearchActivity();
////                homeActivity.
//
//                // final View fragmentView = inflater.inflate(R.layout.fragment_home, container, false);
//                // addChildFragment(R.id.fragmentContainer,HomePageFragment.with(resultWrapper,"tag",adapterPosition));
//
////                getSupportFragmentManager()
////                        .beginTransaction()
////                        .setReorderingAllowed(true) // Optimize for shared element transition
////                      //  .addSharedElement(transitioningView, transitioningView.getTransitionName())
////                        .replace(R.id.fragmentContainer,HomePageFragment.with(resultWrapper,"tag",adapterPosition), HomePageFragment.class
////                                .getSimpleName())
////                        .addToBackStack(HomePageFragment.TAG)
////                        .commit();
//
//
//
//                // ((TransitionSet) getExitTransition()).excludeTarget(view, true);
//
//                //  CustomImageView transitioningView = view.findViewById(R.id.imageViewRectItem);
//
//
//
//
//                // presenter.onItemClick(adapterPosition,transitioningView,view);
//
//            }
//        }));
//
//
//        // adding item touch helper
//        // only ItemTouchHelper.LEFT added to detect Right to Left swipe
//        // if you want both Right -> Left and Left -> Right
//        // add pass ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT as param
//        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT,this );
//        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);





        //  baliAdapter = new MediaListAdapter(this, getActivity());
    }




    @Override
    public void makeToast(String message) {

    }


}
