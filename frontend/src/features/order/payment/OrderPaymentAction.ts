import { createAction } from "@reduxjs/toolkit"
import NewOrderItem from "../models/NewOrderItem"
import OrderPaymentSlice from "./OrderPaymentSlice"

const actions = OrderPaymentSlice.actions
export const CreatePaymentIntentAction = {
    start: actions.createPaymentIntentStart,
    end: actions.createPaymentIntentEnd,
    succeed: actions.createPaymentIntentSucceed,
    fail: actions.createPaymentIntentFail,
    async: createAction<NewOrderItem[]>("order-payment/create-payment-intent/async")
}
