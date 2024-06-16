const getModuleState = state => state.catalog;

export const getMoviesSearch = state =>
    getModuleState(state).moviesSearch?.result;


export const getBillboardDate = state =>
    getModuleState(state).moviesSearch?.date;


export const getMovie = state =>
    getModuleState(state).movie;

export const getSession = state =>
    getModuleState(state).session;


export const getUser = state =>
    getModuleState(state).user;

export const isLoggedIn = state =>
    getUser(state) !== null

export const getUserName = state =>
    isLoggedIn(state) ? getUser(state).userName : null;









