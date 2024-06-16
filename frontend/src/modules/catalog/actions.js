import * as actionTypes from './actionTypes';
import backend from '../../backend';


const findBillboardCompleted = moviesSearch => ({
    type: actionTypes.FIND_BILLBOARD_COMPLETED,
    moviesSearch
});


export const getBillboard = date => dispatch => {

    dispatch(clearBillboardSearch());
    backend.catalogService.findBillboard(date,
        result => dispatch(findBillboardCompleted({date, result})));
}

const clearBillboardSearch = () => ({
    type: actionTypes.CLEAR_BILLBOARD_SEARCH
});


const findMovieByIdCompleted = movie => ({
    type: actionTypes.FIND_MOVIE_BY_ID_COMPLETED,
    movie
});

export const findMovieById = id => dispatch => {
    backend.catalogService.findByMovieId(id,
        movie => dispatch(findMovieByIdCompleted(movie)));
}

const findSessionByIdCompleted = session => ({
    type: actionTypes.FIND_SESSION_BY_ID_COMPLETED,
    session
});

export const findSessionById = (id, onErrors) => dispatch => {
    backend.catalogService.findBySessionId(id,
        movie => dispatch(findSessionByIdCompleted(movie, onErrors)));
}

export const clearMovie = () => ({
    type: actionTypes.CLEAR_MOVIE
});


export const clearSession = () => ({
    type: actionTypes.CLEAR_SESSION
});

