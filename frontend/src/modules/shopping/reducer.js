import {combineReducers} from 'redux';
import * as actionTypes from './actionTypes';

const initialState = {
    lastOrderId: null,
    orderSearch: null,
};

const lastOrderId = (state = initialState.lastOrderId, action) => {

    switch (action.type) {

        case actionTypes.BUY_COMPLETED:
            return action.orderId;

        default:
            return state;
    }

}


const orderSearch = (state = initialState.orderSearch, action) => {

    switch (action.type) {

        case actionTypes.FIND_ORDERS_COMPLETED:
            return action.orderSearch;

        case actionTypes.CLEAR_ORDER_SEARCH:
            return initialState.orderSearch;

        default:
            return state;
    }

}


const reducer = combineReducers({
    lastOrderId,
    orderSearch,
});

export default reducer;


