const getModuleState = state => state.shopping;

export const getLastOrderId = state =>
    getModuleState(state).lastOrderId;

export const getOrderSearch = state =>
    getModuleState(state).orderSearch;
