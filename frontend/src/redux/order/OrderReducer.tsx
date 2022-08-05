import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import HttpResponse from "../models/HttpResponse";
import CachedOrderCreateForm from "./models/CachedOrder";
import Order from "./models/Order";

type State = {
    getOrderListResponse: HttpResponse<Order[]>
    cachedOrderCreateForm: CachedOrderCreateForm
}

const initialState: State = {
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
        cacheOrder: (state: State, action: PayloadAction<CachedOrderCreateForm>)=>{
            state.cachedOrderCreateForm = action.payload
        },
        clearCachedOrder: (state: State)=>{
            state.cachedOrderCreateForm = initialState.cachedOrderCreateForm
        },
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
    }
})

export const OrderReducer = OrderSlice.reducer
export default OrderReducer