import { createAction } from "@reduxjs/toolkit"
import { OrderSlice } from "./OrderReducer"
import PlaceOrderRequest from "./models/CachedOrder"

const actions = OrderSlice.actions
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