import {useEffect} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {FormattedMessage, FormattedNumber} from 'react-intl';
import {useParams} from 'react-router-dom';

import users from '../../users';
import * as selectors from '../selectors';
import * as actions from '../actions';
import {BackLink} from '../../common';

const MovieDetails = () => {

    const movie = useSelector(selectors.getMovie);
    const dispatch = useDispatch();
    const {id} = useParams();

    useEffect(() => {

        const movieId = Number(id);

        if (!Number.isNaN(movieId)) {
            dispatch(actions.findMovieById(movieId));
        }

        return () => dispatch(actions.clearMovie())

    }, [id, dispatch]);

    if (!movie) {
        return null;
    }

    return (

        <div>

            <BackLink/>
            <div className="card text-center">
                <div className="card-body">
                    <h5 className="card-title">{movie.title}</h5>
                    <div className="mt-2"><b>Resumen</b>: {movie.summary}</div>
                    <div className="mt-2"><b>Duración</b>: {movie.durationMinutes} minutos</div>
                </div>
            </div>
        </div>

    );

}

export default MovieDetails;
