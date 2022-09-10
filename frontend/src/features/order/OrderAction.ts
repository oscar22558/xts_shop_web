import { createAction } from "@reduxjs/toolkit"
import { OrderSlice } from "./OrderReducer"

const actions = OrderSlice.actions
export const GetOrderAction = {
    start: actions.getOrderStart,
    end: actions.getOrderEnd,
    succeed: actions.getOrderSucceed,
    fail: actions.getOrderFail,
    async: createAction<number>("get-order/async")
}

export const OrdersAction = {
    start: actions.getOrderListStart,
    end: actions.getOrderListEnd,
    succeed: actions.getOrderListSucceed,
    fail: actions.getOrderListFail,
    async: createAction("get-order-list/async")
}
export const CachedOrderAction = {
    update: actions.cacheOrder,
    clear: actions.clearCachedOrder
}