import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import HttpResponse from "../../models/HttpResponse";
import PaymentIntent from "./models/PaymentIntent";

type State = {
    createPaymentIntentResponse: HttpResponse<PaymentIntent, string>
}

const initialState: State = {
    createPaymentIntentResponse: {
        data: {
            clientSecret: "",
            orderTotal: 0
        },
        error: "",
        loading: false
    }
}

const OrderPaymentSlice = createSlice({
    name: "order-payment",
    initialState,
    reducers: {
        createPaymentIntentStart: (state: State)=>{
            state.createPaymentIntentResponse.loading = true
        },
        createPaymentIntentEnd: (state: State)=>{
            state.createPaymentIntentResponse.loading = false
        },
        createPaymentIntentSucceed: (state: State, {payload}: PayloadAction<PaymentIntent>)=>{
            state.createPaymentIntentResponse.data = payload
        },
        createPaymentIntentFail: (state: State, {payload}: PayloadAction<string>)=>{
            state.createPaymentIntentResponse.error = payload
        },
    }
})
export const OrderPaymentReducer = OrderPaymentSlice.reducer
export default OrderPaymentSlice