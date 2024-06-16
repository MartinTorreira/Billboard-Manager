import {config, appFetch} from './appFetch';

export const buy = (sessionId, numberOfTickets, creditCard, onSuccess, onErrors) => {

    let path = `/buying/buy/${sessionId}`;

    appFetch(path, config('POST', {numberOfTickets, creditCard}), onSuccess, onErrors);
}

export const findOrders = ({page}, onSuccess) =>
    appFetch(`/buying/orders?page=${page}`, config('GET'), onSuccess);

export const deliverTickets = (creditCard, orderId, onSuccess, onErrors) =>
    appFetch(`/buying/deliver`, config('POST', {creditCard,orderId}), onSuccess, onErrors);