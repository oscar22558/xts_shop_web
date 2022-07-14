import { createAction } from "@reduxjs/toolkit"
import { OrderSlice } from "./OrderReducer"
import PlaceOrderRequest from "./models/CachedOrder"

const actions = OrderSlice.actions
export const PlaceOrderAction = {
    start: actions.postOrderStart,
    end: actions.postOrderEnd,
    succeed: actions.postOrderSucceed,
    fail: actions.postOrderFail,
    async: createAction<PlaceOrderRequest>("post-order/async")
}
export const CachedOrderAction = {
    update: actions.cacheOrder,
    clear: actions.clearCachedOrder
}