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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.shamildev.retro.domain.config.DataConfig;
import com.shamildev.retro.domain.repository.FirebaseRepository;
import com.shamildev.retro.domain.repository.RemoteRepository;

import javax.inject.Inject;

import dagger.Reusable;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;


/**
 * An implementation of {@link RemoteRepository}.
 */
@Reusable
public final class FirebaseRepositoryImpl implements FirebaseRepository {



    private final DataConfig dataConfig;


     private FirebaseAuth mAuth;




    @Inject
    FirebaseRepositoryImpl(
                           DataConfig dataConfig) {

        this.mAuth = FirebaseAuth.getInstance();
        this.dataConfig = dataConfig;
        Log.e("FirebaseRepositoryImpl","");


    }




    @Override
    public Flowable<String> checkUser() {
        Log.e("FIREBASE","check user shamil@aaa.de ");
       // FirebaseUser currentUser = mAuth.getCurrentUser();


        return Flowable.create(e -> {

            try {

                mAuth.signInWithEmailAndPassword("shamil@aaa.de", "123456")

                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d("TAG", "createUserWithEmail:onComplete");
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("TAG", "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    user.getEmail();

                                   e.onNext(user.getUid());
                                   e.onComplete();

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


            }catch (Exception t) {
                e.onError(t);
            }





        }, BackpressureStrategy.BUFFER);






    }
}
