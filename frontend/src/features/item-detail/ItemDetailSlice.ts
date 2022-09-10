import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { stat } from "fs";
import Item from "../items/models/Item";
import HttpResponse from "../models/HttpResponse";

type State = {
    getItemDetailResponse: HttpResponse<Item|null, string>
}

const initialState: State = {
    getItemDetailResponse: {
        data: null,
        error: "",
        loading: false
    }
}

const ItemDetailSlice = createSlice({
    name: "item-detail",
    initialState,
    reducers: {
        getItemDetailStart: (state: State)=>{
            state.getItemDetailResponse.loading = true
        },
        getItemDetailEnd: (state: State)=>{
            state.getItemDetailResponse.loading = false
        },
        getItemDetailSucceed: (state: State, {payload}: PayloadAction<Item>)=>{
            state.getItemDetailResponse.error = ""
            state.getItemDetailResponse.data = payload
        },
        getItemDetailFail: (state: State, {payload}: PayloadAction<string>)=>{
            state.getItemDetailResponse.error = payload
        },
    }
})
export const ItemDetailReducer = ItemDetailSlice.reducer
export default ItemDetailSlice