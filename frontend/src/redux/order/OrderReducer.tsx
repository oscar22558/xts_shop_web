import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import HttpResponse from "../models/HttpResponse";
import CachedOrder from "./models/CachedOrder";

type State = {
    postOrder: HttpResponse<boolean| null>,
    cachedOrder: CachedOrder
}

const initialState: State = {
    postOrder: {
        data: null,
        error: null,
        loading: false
    },
    cachedOrder: {
       itemIds: [],
       address: {
            row1: "",
            row2: "",
            district: "",
            area: ""
       } 
    }
}

export const OrderSlice = createSlice({
    name: "order",
    initialState,
    reducers: {
        cacheOrder: (state: State, action: PayloadAction<CachedOrder>)=>{
            state.cachedOrder = action.payload
        },
        clearCachedOrder: (state: State)=>{
            state.cachedOrder = initialState.cachedOrder
        },
        postOrderStart: (state: State)=>{
            state.postOrder.loading = true
        },
        postOrderEnd: (state: State)=>{
            state.postOrder.loading = false
        },
        postOrderSucceed: (state: State)=>{
            state.postOrder.data = true
            state.postOrder.error = null
        },
        postOrderFail: (state: State, action: PayloadAction<string>)=>{
            state.postOrder.data = false
            state.postOrder.error = action.payload
        },
    }
})

export const OrderReducer = OrderSlice.reducer
export default OrderReducer