/*
 * Copyright 2017 Vandolf Estrellado
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.shamildev.retro.data.repository;


import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.shamildev.retro.data.net.error.BaseError;
import com.shamildev.retro.domain.config.DataConfig;
import com.shamildev.retro.domain.models.AppUser;
import com.shamildev.retro.domain.repository.BaseRepository;
import com.shamildev.retro.domain.repository.RemoteRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.Flowable;


/**
 * An implementation of {@link RemoteRepository}.
 */
@Singleton
public final class FirebaseRepositoryImpl implements BaseRepository {


    private final DataConfig dataConfig;


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    @Inject
    AppUser appUser;


    @Inject
    FirebaseRepositoryImpl(
            DataConfig dataConfig) {
        Log.e("FirebaseRepositoryImpl", "");

        this.mAuth = FirebaseAuth.getInstance();
        this.dataConfig = dataConfig;


    }


//    public Task<AuthResult> getAuthWithFacebook(AccessToken token) {
//        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
//        return mAuth.signInWithCredential(credential);
//    }




    @Override
    public Flowable<String> initUser() {
//        FirebaseUser user = mAuth.getCurrentUser();
//        if(user!=null){
//            return Flowable.empty();
//        }
        return Flowable.empty();
    }


    public Flowable<AppUser> signInAnonym() {
        return Flowable.create(e -> {
            try {

                mAuth.signInAnonymously()

                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d("TAG", "signInWithEmailAndPassword:onComplete");
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser currentUser = mAuth.getCurrentUser();
                                    if (currentUser.isAnonymous()) {
                                        currentUser.getEmail();
                                        currentUser.getDisplayName();
                                        appUser.setEmail(currentUser.getEmail());
                                        appUser.setName(currentUser.getDisplayName());
                                        appUser.setUid(currentUser.getUid());
                                        e.onNext(appUser);
                                        e.onComplete();

                                    } else {
                                        e.onComplete();


                                    }


                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("TAG", "signInWithEmail:failure", task.getException());

                                }

                                // [START_EXCLUDE]
                                if (!task.isSuccessful()) {
                                    Log.w("TAG", "signInWithEmail:failure");
                                    e.onComplete();

                                }


                            }
                        });


            } catch (Exception t) {
                e.onError(t);
            }

        }, BackpressureStrategy.BUFFER);
    }

    @Override
    public Flowable<AppUser> signIn() {
        FirebaseUser user = mAuth.getCurrentUser();
        return Flowable.create(e -> {
            try {
                if (user == null) {
                    mAuth.signInWithEmailAndPassword("shamil@aaa.de", "12345")
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Log.d("TAG", "signInWithEmailAndPassword:onComplete");
                                    if (task.isSuccessful()) {
                                        FirebaseUser currentUser = mAuth.getCurrentUser();

                                        if (currentUser != null) {
                                            appUser.setFirebaseUser(currentUser.getUid(), currentUser.getEmail(), currentUser.getDisplayName(),currentUser.getProviderId(), currentUser.getPhotoUrl().getPath());
                                            e.onNext(appUser);
                                            e.onComplete();
                                        } else {
                                            e.onComplete();
                                        }
                                    } else {
                                        e.onError(new BaseError(401,"Sign in with Email and Password failed"));

                                    }

                                }
                            })
                    ;
                }


            } catch (Exception t) {
                e.onError(t);
            }


        }, BackpressureStrategy.BUFFER);
    }

    @Override
    public Flowable<AppUser> signIn(String token) {
        return signInWithFacebook(token);
    }



    @Override
    public Flowable<String> signInWithFacebook() {
        return null;
    }

    public Flowable<AppUser> signInWithFacebook(String token) {
        Log.e("facebook#", "token "+token);

        return
                 Flowable.create(e -> {
                    try {
                        if (token != null) {
                            Log.e("signInWithFacebook", "check user ");
                            AuthCredential credential = FacebookAuthProvider.getCredential(token);
                            mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser currentUser = mAuth.getCurrentUser();
                                        if (currentUser != null) {
                                           // it(currentUser);
                                            appUser.setFirebaseUser(currentUser.getUid(), currentUser.getEmail(), currentUser.getDisplayName(), currentUser.getProviderId(),currentUser.getPhotoUrl().getPath());
                                            e.onNext(appUser);
                                            e.onComplete();
                                        } else {
                                            e.onComplete();
                                        }
                                    } else {
                                        e.onError(new BaseError(401,"Sign in with Facebook failed"));

                                    }
                                }
                            });
                        }
                    } catch (Exception t) {
                        e.onError(t);
                    }


                }, BackpressureStrategy.BUFFER);
    }

    @Override
    public Flowable<AppUser> checkUser() {
        Log.e("FIREBASE", "check user " + mAuth.getCurrentUser());
        if (mAuth.getCurrentUser() != null) {
            return Flowable.just(appUser)
                    .map(appUser -> {
                        appUser.setFirebaseUser(mAuth.getCurrentUser().getUid(), mAuth.getCurrentUser().getEmail(), mAuth.getCurrentUser().getDisplayName(),mAuth.getCurrentUser().getProviderId(),mAuth.getCurrentUser().getPhotoUrl().getPath());
                        return appUser;
                    });
        }

        return Flowable.empty();

    }


    private void listener() {

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                Log.e("FIREBASE", "onAuthStateChanged " + currentUser.getEmail());


            }
        };

    }

    //return Flowable.empty();

//        return Flowable.create(e -> {
//
//            try {
//
//                mAuth.signInWithEmailAndPassword("shamil@aaa.de", "123456")
//
//                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                Log.d("TAG", "signInWithEmailAndPassword:onComplete");
//                                if (task.isSuccessful()) {
//                                    // Sign in success, update UI with the signed-in user's information
//
//                                    FirebaseUser user = mAuth.getCurrentUser();
//                                    user.getEmail();
//                                    user.getDisplayName();
//                                    Log.d("TAG", "signInWithEmail:success"+user.getEmail()+" "+user.getDisplayName()+" "+user.getUid());
//                                    appUser.setEmail(user.getEmail());
//                                    e.onNext(user.getUid());
//                                    e.onComplete();
//
//                                } else {
//                                    // If sign in fails, display a message to the user.
//                                    Log.w("TAG", "signInWithEmail:failure", task.getException());
//
//                                }
//
//                                // [START_EXCLUDE]
//                                if (!task.isSuccessful()) {
//                                    Log.w("TAG", "signInWithEmail:failure");
//                                    e.onComplete();
//
//                                }
//
//
//                            }
//                        });
//
//
//            }catch (Exception t) {
//                e.onError(t);
//            }
//
//
//
//
//
//        }, BackpressureStrategy.BUFFER);




    @Override
    public Completable signOut() {
        return Completable.create(e -> {
            if (this.mAuth.getCurrentUser() != null) {
                mAuth.signOut();
                appUser.removetFirebaseUser();
                e.onComplete();
            }
            // e.onError(new Exception("User not logged in!"));
        });


    }
}
