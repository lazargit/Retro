package com.shamildev.retro.domain.bootstrap;
import com.shamildev.retro.domain.executor.UseCaseHandler;
import com.shamildev.retro.domain.interactor.UseCaseFlowable;
import com.shamildev.retro.domain.params.ParamsBasic;
import com.shamildev.retro.domain.util.Pair;
import java.util.ArrayList;
import javax.inject.Inject;
import io.reactivex.subscribers.DisposableSubscriber;


/**
 * Created by Schamil Mischijew on 30.11.2017.
 */

public class BootstrapImpl {

    private final String TAG = this.getClass().getSimpleName();
    private Bootstrap onBootstrap;
    private ArrayList<Pair<UseCaseFlowable, ParamsBasic>> useCases;
    private UseCaseHandler useCaseHandler;


    @Inject
    public BootstrapImpl(UseCaseHandler useCaseHandler) {
     useCases = new ArrayList<>();
     this.useCaseHandler = useCaseHandler;


    }

    public void setUp(Bootstrap onBootstrap) {
      this.onBootstrap=onBootstrap;
    }


    public void addUseCase( Pair<UseCaseFlowable, ParamsBasic> toAdd) {
        useCases.add(toAdd);
    }


    public void executeUseCases() {
        System.out.println("startBootstrap");

        if(useCases.size()>0){


            useCaseHandler.execute(useCases.get(0).key, useCases.get(0).value, new DisposableSubscriber<Object>() {
                @Override
                public void onNext(Object o) {

                    System.out.println("Bootstrap#UseCase#OnNext"+ o.getClass().getSimpleName());

                }

                @Override
                public void onError(Throwable t) {
                    onBootstrap.onBootstrapError(t);
                    cleartBootstrap();
                }



                @Override
                public void onComplete() {
                     onBootstrap.onBootstrapNext(useCases.get(0).key.getClass());
                     useCases.remove(useCases.get(0));
                     executeUseCases();

                }
            });

        }else {
            this.onBootstrap.onBootstrapComplete();

        }


    }

    public void cleartBootstrap() {
        if(useCases.size()>0){
            useCaseHandler.clear();
            for (Pair<UseCaseFlowable, ParamsBasic> useCaseParamsBasicPair : useCases) {
               // useCaseParamsBasicPair.first.dispose();
            }
        }

    }


}
