import {combineReducers} from 'redux';

import * as actionTypes from './actionTypes';

const initialState = {
    moviesSearch: null,
    movie: null,
    session: null
};


const moviesSearch = (state = initialState.moviesSearch, action) => {

    switch (action.type) {

        case actionTypes.FIND_BILLBOARD_COMPLETED:
            return action.moviesSearch;

        case actionTypes.CLEAR_BILLBOARD_SEARCH:
            return initialState.moviesSearch;

        default:
            return state;
    }
}


const movie = (state = initialState.movie, action) => {

    switch (action.type) {

        case actionTypes.FIND_MOVIE_BY_ID_COMPLETED:
            return action.movie;

        case actionTypes.CLEAR_MOVIE:
            return initialState.movie;

        default:
            return state;

    }
}

const session = (state = initialState.session, action) => {

    switch (action.type) {

        case actionTypes.FIND_SESSION_BY_ID_COMPLETED:
            return action.session;

        case actionTypes.CLEAR_SESSION:
            return initialState.session;

        default:
            return state;

    }
}



const reducer = combineReducers({
    moviesSearch,
    movie,
    session
});

export default reducer;