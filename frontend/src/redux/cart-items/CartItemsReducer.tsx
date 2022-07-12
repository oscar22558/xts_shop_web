import { Action, createReducer, createSlice, PayloadAction } from "@reduxjs/toolkit";
import { stat } from "fs";
import Item from "../items/models/Item";
import HttpResponse from "../models/HttpResponse";

type State = {
    itemsInCart: HttpResponse<Item[]>
}

const initialState: State = {
    itemsInCart: {
        data: [],
        error: null,
        loading: false
    }
}

const CartItemsSlice = createSlice({
    name: "CartItems",
    initialState,
    reducers: {
        getItemsByIdStart: (state: State)=>{
            state.itemsInCart.loading = true
        },
        getItemsByIdEnd: (state: State)=>{
            state.itemsInCart.loading = false
        },
        getItemsByIdSuccess: (state: State, action: PayloadAction<Item[]>)=>{
            state.itemsInCart.data = action.payload
        },
        getItemsByIdFail: (state: State, action: PayloadAction<string>)=>{
            state.itemsInCart.error = action.payload
        },
    }
})

const CartItemsReducer = CartItemsSlice.reducer
export const CartItemsSliceAction = CartItemsSlice.actions
export default CartItemsReducer
