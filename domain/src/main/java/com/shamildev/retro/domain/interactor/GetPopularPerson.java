
package com.shamildev.retro.domain.interactor;


import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.domain.params.ParamsBasic;
import com.shamildev.retro.domain.repository.RemoteRepository;

import javax.inject.Inject;

import io.reactivex.Flowable;



public final class GetPopularPerson implements UseCaseFlowable<ParamsBasic,ResultWrapper> {

    private final RemoteRepository repository;

    @Inject
    GetPopularPerson(RemoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flowable<ResultWrapper> execute(ParamsBasic params) {

        int page = ((Params) params).page;
        return this.repository.fetchPopularPerson(page);

    }




    public static final class Params implements ParamsBasic {

        private Params() {

        }


        private int page = 1;



        private Params(int page) {
            this.page = page;
        }



        public static Params withPage(int page) {
            return new Params(page);
        }



    }
}

