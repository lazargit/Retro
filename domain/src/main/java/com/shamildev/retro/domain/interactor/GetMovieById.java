
package com.shamildev.retro.domain.interactor;


import com.shamildev.retro.domain.models.Images;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.params.ParamsBasic;
import com.shamildev.retro.domain.repository.RemoteRepository;
import com.shamildev.retro.domain.util.Constants;

import org.reactivestreams.Publisher;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;


/**
 * Use case for getting a businesses with a given id.
 */
public final class GetMovieById implements UseCaseFlowable<ParamsBasic,Movie> {

    private final RemoteRepository repository;

    @Inject
    GetMovieById(RemoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flowable<Movie> execute(ParamsBasic params) {
        Long movieId = ((Params) params).movieId;
        String append_to_response = ((Params) params).append_to_response;
        String language = ((Params) params).language;
        String str = Constants.INCLUDE_IMAGE_LANGUAGE_VALUE + "," + ((Params) params).language;





        return this.repository.fetchMovie(movieId,append_to_response,str)
                          .flatMap(movie -> repository.fetchImages(movie.id())
                          .map(movie::setImages))
                          .flatMap(movie -> repository.fetchCredits(movie.id())
                          .map(movie::setCredits))

                        ;

    }




    public static final class Params implements ParamsBasic {

        private Params() {

        }

        private Long movieId = 0L;
        private String language;
        private String append_to_response;


        private Params(Long movieId) {
            this.movieId = movieId;
        }
        private Params(Long movieId,String append_to_response ) {
            this.movieId = movieId;
            this.append_to_response = append_to_response;
        }
        private Params(Long movieId,String language,String append_to_response ) {
            this.movieId = movieId;
            this.language = language;
            this.append_to_response = append_to_response;
        }


        public static Params with(Long movieId) {
            return new Params(movieId);
        }
        public static Params with(Long movieId, String append_to_response) {
            return new Params(movieId,append_to_response);
        }
        public static Params with(Long movieId, String language, String append_to_response) {

            return new Params(movieId,language,append_to_response);
        }


    }
}

