package com.shamildev.retro.ui.account;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.util.Base64;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.shamildev.retro.R;
import com.shamildev.retro.ui.account.fragment.view.AccountFragment;
import com.shamildev.retro.ui.common.BaseActivitySupport;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by Schamil Lazar.
 */

public class AccountActivity extends BaseActivitySupport {




    private CallbackManager mCallbackManager;
    public CallbackManager getmCallbackManager() {
        return mCallbackManager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //printhashkey();


        mCallbackManager = CallbackManager.Factory.create();
       // LoginButton loginButton = findViewById(R.id.button_fblogin);
        //loginButton.setReadPermissions(Arrays.asList("email"));
        // If using in a fragment
        //loginButton.setFragment(this);
      //  LoginManager.setReadPermissions("email", "public_profile");
        LoginManager.getInstance().logInWithReadPermissions(
                this,
                Arrays.asList("email","public_profile"));

        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, new AccountFragment());
        }
    }

    public void printhashkey(){

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.shamildev.retro",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

    }


    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, AccountActivity.class);
        return intent;
    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK

        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
