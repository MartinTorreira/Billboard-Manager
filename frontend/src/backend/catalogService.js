import {config, appFetch} from './appFetch';

export const findBillboard = (date, onSuccess) => {

    let path = `/catalog/billboard?`;
    path += date != null ? `date=${date}` : "";

    appFetch(path, config('GET'), onSuccess);
}

export const findByMovieId = (id, onSuccess) =>
    appFetch(`/catalog/movies/${id}`, config('GET'), onSuccess);


export const findBySessionId = (id, onSuccess) =>
    appFetch(`/catalog/sessions/${id}`, config('GET'), onSuccess);