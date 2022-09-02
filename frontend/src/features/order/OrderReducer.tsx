import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import HttpResponse from "../models/HttpResponse";
import CachedOrderCreateForm from "./models/CachedOrder";
import Order from "./models/Order";

type State = {
    getOrderResponse: HttpResponse<Order|null>
    getOrderListResponse: HttpResponse<Order[]>
    cachedOrderCreateForm: CachedOrderCreateForm
}

const initialState: State = {
    getOrderResponse: {
        data: null,
        error: null,
        loading: false
    },
    getOrderListResponse: {
        data: [],
        error: null,
        loading: false
    },
    cachedOrderCreateForm: {
       userAddressId: -1,
       clientSecret: ""
    }
}

export const OrderSlice = createSlice({
    name: "order",
    initialState,
    reducers: {
        getOrderListStart: ({getOrderListResponse}: State)=>{
            getOrderListResponse.loading = true
        },
        getOrderListEnd: ({getOrderListResponse}: State)=>{
            getOrderListResponse.loading = false
        },
        getOrderListSucceed: ({getOrderListResponse}: State, {payload}: PayloadAction<Order[]>)=>{
            getOrderListResponse.data = payload
            getOrderListResponse.error = null
        },
        getOrderListFail: ({getOrderListResponse}: State, {payload}: PayloadAction<string>)=>{
            getOrderListResponse.data = []
            getOrderListResponse.error = payload
        },
        getOrderStart: (state: State)=>{
            state.getOrderResponse.loading = true
        },
        getOrderEnd: (state: State)=>{
            state.getOrderResponse.loading = false
        },
        getOrderSucceed: (state: State, {payload}: PayloadAction<Order>)=>{
            state.getOrderResponse.error = null
            state.getOrderResponse.data = payload
        },
        getOrderFail: (state: State, {payload}: PayloadAction<string>)=>{
            state.getOrderResponse.error = payload
        },
        cacheOrder: (state: State, action: PayloadAction<CachedOrderCreateForm>)=>{
            state.cachedOrderCreateForm = action.payload
        },
        clearCachedOrder: (state: State)=>{
            state.cachedOrderCreateForm = initialState.cachedOrderCreateForm
        },
    }
})

export const OrderReducer = OrderSlice.reducer
export default OrderReducer