package com.shamildev.retro.ui.account.fragment.presenter;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */

import com.shamildev.retro.domain.MediaItem;
import com.shamildev.retro.ui.common.presenter.Presenter;

/**
 * A {@link Presenter} that does some work and shows the results.
 */
public interface AccountPresenter extends Presenter {

    void toast(Object obj);
    void login();
    void logout();
    void onError(Throwable t);

    void logoutSuccesfull();

    void setBackgroundImage(MediaItem results);

    void signSuccesfull();

    void fbLogin(String token);
}
