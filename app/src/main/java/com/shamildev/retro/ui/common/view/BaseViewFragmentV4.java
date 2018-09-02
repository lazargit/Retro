package com.shamildev.retro.ui.common.view;

/**
 * Created by Shamil Lazar.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.shamildev.retro.ui.common.BaseActivityModule;
import com.shamildev.retro.ui.common.presenter.Presenter;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.DaggerDialogFragment;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerFragment;

/**
 * A {@link BaseFragment} that contains and invokes {@link Presenter} lifecycle invocations.
 *
 * @param <T> the type of the {@link Presenter}.
 */
public abstract class BaseViewFragmentV4<T extends Presenter> extends DaggerFragment implements MVPView {

    private Unbinder viewUnbinder;
    @Inject
    protected T presenter;
    @Inject
    @Named(BaseFragmentModule.CHILD_FRAGMENT_V4_MANAGER)
    protected FragmentManager childFragmentManager;



    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        presenter.onStart(savedInstanceState);
        viewUnbinder = ButterKnife.bind(this, getView());
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @CallSuper
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroyView() {
        presenter.onEnd();
        super.onDestroyView();
    }
    /**
     * Shows a {@link Toast} message.
     *
     * @param message An string representing a message to be shown.
     */
    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    protected final void addChildFragment(@IdRes int containerViewId, Fragment fragment) {
        childFragmentManager.beginTransaction()
                .add(containerViewId, fragment)
                .commit();
    }




}