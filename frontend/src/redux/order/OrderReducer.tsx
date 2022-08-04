import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import HttpResponse from "../models/HttpResponse";
import CachedOrderCreateForm from "./models/CachedOrder";

type State = {
    postOrder: HttpResponse<boolean| null>,
    cachedOrderCreateForm: CachedOrderCreateForm
}

const initialState: State = {
    postOrder: {
        data: null,
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